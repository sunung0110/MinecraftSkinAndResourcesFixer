package minecraftskinandresourcesfixer;

//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
//import java.util.Base64;
import java.util.HashMap;

//import javax.imageio.ImageIO;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
import com.sun.net.httpserver.HttpExchange;


public class ResourcesFixer {
	
	private static SuperSimpleLogger logger;
	private static HashMap<String, byte[]> remoteFileCache = new HashMap<String, byte[]>();
	
	public static void gimmeLogger(SuperSimpleLogger logger) {
		ResourcesFixer.logger = logger;
	}
	
	public static void runResourcesFixer(String connectionId, HttpExchange exchange, String hostname, String url) throws IOException {
		byte[] resourcesFile = doRunResourcesFixer(connectionId, hostname, url);
		if(resourcesFile.length != 0) {
			exchange.getResponseHeaders().add("Content-Type", "file/txt");
			exchange.sendResponseHeaders(200, resourcesFile.length);
			exchange.getResponseBody().write(resourcesFile);
			exchange.close();
		} else {
			exchange.sendResponseHeaders(404, 0);
			exchange.close();
		}
		logger.log("[Resources Fixer] " + connectionId + "Request served successfully. HTTP Connection to game closed.");
	}
	
	private static byte[] doRunResourcesFixer(String connectionId, String hostname, String url) throws IOException {
		String resourcesType;
		if(hostname.equals("www.minecraft.net") && url.startsWith("/resources/")) {
			resourcesType = "OldResources";
			logger.log("[Resources Fixer] " + connectionId + "Received Generation 1 request for Resources");
		} else if(hostname.equals("s3.amazonaws.com") && url.startsWith("/MinecraftResources/")) {
			resourcesType = "NewResources";
			logger.log("[Resources Fixer] " + connectionId + "Received Generation 2 request for Resources");
		} else {
			throw new RuntimeException();
		}
		
		
		String resourcesURL;
		URL rURL;
		
		if(resourcesType.equals("OldResources")){
			
			if(url.equals("/resources/")){
				rURL = ResourcesFixer.class.getResource ("/resources");
			} else{
				rURL = ResourcesFixer.class.getResource (url);
			}
		} else{
			
			rURL = ResourcesFixer.class.getResource ("/MinecraftResources");
			
		}
		
		
		
		resourcesURL = rURL.toString();
		
		
		
		return getRemoteFile(resourcesURL, connectionId);
	}
	
	private static byte[] getRemoteFile(String url, String connectionId) throws IOException {
		if(remoteFileCache.containsKey(url)) {
			logger.log("[Load] " + connectionId + "No need to Loading " + url + " - file is stored in cache.");
			return remoteFileCache.get(url);
		} else {
			logger.log("[Load] " + connectionId + "Downloading " + url);
			ByteArrayOutputStream baos = new ByteArrayOutputStream(180224);
			URLConnection conn = new URL(url).openConnection();
			InputStream is = conn.getInputStream();
			byte[] buff = new byte[180224];
			int read;
			while((read = is.read(buff)) != -1) {
				baos.write(buff, 0, read);
			}
			byte[] file = baos.toByteArray();
			baos.close();
			logger.log("[Load] " + connectionId + "Successfully loaded " + url + " (" + file.length + " bytes)");
			remoteFileCache.put(url, file);
			return file;
		}
	}

}

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


public class LoginFixer {
	
	private static SuperSimpleLogger logger;
	private static HashMap<String, byte[]> remoteFileCache = new HashMap<String, byte[]>();
	
	public static void gimmeLogger(SuperSimpleLogger logger) {
		LoginFixer.logger = logger;
	}
	
	public static void runLoginFixer(String connectionId, HttpExchange exchange, String hostname, String url) throws IOException {
		if(url.startsWith("/game/")) {
			logger.log("[Resources Fixer] " + connectionId + "Received Login request");
			exchange.getResponseHeaders().add("Content-Type", "file/txt");
			exchange.sendResponseHeaders(200, 1);
			exchange.getResponseBody().write('0');
			exchange.close();
		} else {
			exchange.sendResponseHeaders(404, 0);
			exchange.close();
		}
		logger.log("[Resources Fixer] " + connectionId + "Request served successfully. HTTP Connection to game closed.");
	}

}

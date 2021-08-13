package minecraftskinfixer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLConnection;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HTTPProxyServer implements HttpHandler {
	
	private static SuperSimpleLogger logger;
	private static int connectionIdCounter = 0;
	
	public static void start(int port, SuperSimpleLogger logger) {
		HTTPProxyServer.logger = logger;
		HttpServer server = null;
		try {
			server = HttpServer.create(new InetSocketAddress("127.0.0.1", port), 0);
		} catch (IOException e) {
			logger.log("[HTTP Proxy] Unable to create HTTP Server: " + e.getClass().getName() + ": " + e.getMessage());
			return;
		}
		server.createContext("/", new HTTPProxyServer());
		server.start();
		logger.log("[HTTP Proxy] HTTP Server successfully started on port " + port);
	}

	@Override
	public void handle(HttpExchange he) throws IOException {
		String connectionId = "[HTTP #" + connectionIdCounter++ + "] ";
		Headers requestHeaders = he.getRequestHeaders();
		String requestedHost = requestHeaders.get("Host").get(0);
		String requestedUrl = he.getRequestURI().toString();
		if(requestedUrl.contains(requestedHost)) {
			requestedUrl = requestedUrl.substring(requestedUrl.indexOf(requestedHost) + requestedHost.length());
		}
		logger.log("[HTTP Proxy] " + connectionId + "Received a HTTP " + he.getRequestMethod() + " request for " + requestedHost + requestedUrl);
		if(he.getRequestMethod().equals("GET") && (
				requestedHost.equals("www.minecraft.net") && requestedUrl.startsWith("/skin/") ||
				requestedHost.equals("s3.amazonaws.com") && requestedUrl.startsWith("/MinecraftSkins/") ||
				requestedHost.equals("s3.amazonaws.com") && requestedUrl.startsWith("/MinecraftCloaks/") ||
				requestedHost.equals("skins.minecraft.net") && requestedUrl.startsWith("/MinecraftSkins/") ||
				requestedHost.equals("skins.minecraft.net") && requestedUrl.startsWith("/MinecraftCloaks/"))) {
			logger.log("[HTTP Proxy] " + connectionId + "Identified request as Minecraft skin request - forwarding to Minecraft skin request handler.");
			SkinFixer.runSkinFixer(connectionId, he, requestedHost, requestedUrl);
		} else if(he.getRequestMethod().equals("GET") && (
				requestedHost.equals("www.minecraft.net") && requestedUrl.startsWith("/resources/") ||
				requestedHost.equals("s3.amazonaws.com") && requestedUrl.startsWith("/MinecraftResources/"))){
			logger.log("[HTTP Proxy] " + connectionId + "Identified request as Minecraft resources request - forwarding to Minecraft resources request handler.");
			SkinFixer.runSkinFixer(connectionId, he, requestedHost, requestedUrl);
		} else if(he.getRequestMethod().equals("GET")) {
			logger.log("[HTTP Proxy] " + connectionId + "Request does not look like a Minecraft skin request - being a really bad but at least possibly kind of working proxy server.");
			URL url = new URL("http://" + requestedHost + requestedUrl);
			URLConnection conn = url.openConnection();
			he.sendResponseHeaders(200, conn.getContentLengthLong());
			InputStream is = conn.getInputStream();
			OutputStream os = he.getResponseBody();
			byte[] buff = new byte[4096];
			int read;
			while((read = is.read(buff)) != -1) {
				os.write(buff, 0, read);
			}
			he.close();
		} else {
			logger.log("[HTTP Proxy] " + connectionId + "Request is not a GET request and therefore not supported by this program. Responding with 500 Internal Server Error.");
			he.sendResponseHeaders(500, 0);
			he.close();
		}
	}

}

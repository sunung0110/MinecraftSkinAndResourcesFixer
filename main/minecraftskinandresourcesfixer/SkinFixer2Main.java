package minecraftskinandresourcesfixer;

public class SkinFixer2Main {
	
	public static final String VERSION = "4.2";
	
	private static SuperSimpleLogger logger;
	
	public static void main(String[] args) {
		logger = new LogWindow();
		SkinFixer.gimmeLogger(logger);
		ResourcesFixer.gimmeLogger(logger);
		LoginFixer.gimmeLogger(logger);
		SaveFixer.gimmeLogger(logger);
		logger.log("Welcome to Minecraft Skin And Resources Fixer " + VERSION);
		logger.log("Made by https://github.com/DoubleNegation And editted by https://github.com/sunung0110");
		logger.log("Source code available at https://github.com/sunung0110/MinecraftSkinAndResourcesFixer");
		logger.log("Running on Java " + System.getProperty("java.version"));
		logger.log("Close this window to exit.");
		logger.log("(Build Date: 2023-02-04)");
		logger.log("----------------------------------------");
		int overrideHttpPort = -1;
		boolean printHelp = false;
		for(String s : args) {
			if(s.startsWith("--httpport=")) {
				String portStr = s.substring("--httpport=".length());
				int portNum = -1;
				try {
					portNum = Integer.parseInt(portStr);
				} catch(NumberFormatException e) {
					logger.log("WARNING: Invalid value for command line argument httpport: \"" + portStr + "\" is not a number.");
				}
				if(!(portNum >= 1 && portNum <= 65535)) {
					logger.log("WARNING: Invalid port number for httpport - Must be in range 1..65535");
					portNum = -1;
				}
				overrideHttpPort = portNum;
			} else if(s.equals("--help")) {
				printHelp = true;
			} else if(s.equals("--no-convert")) {
				logger.log("Skin converter was disabled via command-line argument.");
				SkinFixer.CONVERT_MODE = 1;
			} else if(s.startsWith("--flip-bottoms=")) {
				String valueStr = s.substring("--flip-bottoms=".length());
				if(valueStr.equals("on")) {
					if(SkinFixer.CONVERT_MODE != 1) SkinFixer.CONVERT_MODE = 3;
				} else if(valueStr.equals("off")) {
					if(SkinFixer.CONVERT_MODE != 1) SkinFixer.CONVERT_MODE = 2;
				} else if(valueStr.equals("auto")) {
					if(SkinFixer.CONVERT_MODE != 1) SkinFixer.CONVERT_MODE = 0;
				} else {
					logger.log("WARNING: Invalid value \"" + valueStr + "\" for option --flip-bottoms. Allowed values are: on, off, auto");
				}
			}
		}
		int usedHttpPort = overrideHttpPort == -1 ? 8080 : overrideHttpPort;
		if(overrideHttpPort == -1) {
			logger.log("Using default port 8080 for HTTP proxy.");
		} else {
			logger.log("Using user-specified port " + usedHttpPort + " for HTTP proxy.");
		}
		if(SkinFixer.CONVERT_MODE == 2) logger.log("Bottom sides of skins will never be flipped (specified with argument --flip-bottoms=off)");
		else if(SkinFixer.CONVERT_MODE == 3) logger.log("Bottom sides of skins will always be flipped (specified with argument --flip-bottoms=on)");
		else if(SkinFixer.CONVERT_MODE == 1) logger.log("Skin converter mode is on automatic mode. This will not work with all versions from Minecraft 1.0 to Minecraft 1.2.5. Specify argument --flip-bottoms=off to use MinecraftSkinFixer with thoes versions.");
		logger.log("Use command-line option --help to get a list of available command-line arguments.");
		if(printHelp) {
			logger.log("----------------------------------------");
			logger.log("Available Command-Line options:");
			logger.log("--help                       Causes this help screen to be displayed.");
			logger.log("--no-convert                 Causes the skin converter to be disabled.");
			logger.log("--flip-bottoms=on|off|auto   Changes the behaviour of the skin converter.");
			logger.log("                             This has no effect if --no-convert is specified.");
			logger.log("                             Default: auto");
			logger.log("                             auto does not work with versions 1.0 - 1.2.5. Use off for these versions.");
			logger.log("--httpport=<portnum>         Causes SkinFixer to use a different port for the HTTP proxy (default is 8080).");
		}
		logger.log("----------------------------------------");
		logger.log("Add the following arguments to the \"JVM Arguments\" option of your minecraft launch configuration / launcher profile to use the skin fixer:");
		logger.log("-Dhttp.proxyHost=127.0.0.1");
		logger.log("-Dhttp.proxyPort=" + usedHttpPort);
		logger.log("----------------------------------------");
		HTTPProxyServer.start(usedHttpPort, logger);
		logger.log("----------------------------------------");
	}
	
}

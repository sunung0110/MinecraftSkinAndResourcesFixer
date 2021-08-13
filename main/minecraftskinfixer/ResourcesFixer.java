package minecraftskinandresourcesfixer;

import java.io.InputStream;

public class ResourcesFixer {

    public static InputStream accessFile(byte Resource_MODE) {
        String resource;
		
		if(Resource_MODE == 1) {
			resource = "resources";
		} else if(Resource_MODE == 2) {
			resource = "MinecraftResources";
		} else {
			resource = "0";
		}

        // this is the path within the jar file
        InputStream input = ResourcesFixer.class.getResourceAsStream("/" + resource);
        if (input == null) {
            // this is how we load file within editor (eg eclipse)
            input = ResourcesFixer.class.getClassLoader().getResourceAsStream(resource);
        }

        return input;
    }
}
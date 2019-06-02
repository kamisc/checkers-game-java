package pl.sewerynkamil.game;

import java.net.URL;

public class Resources {

    public static String getPath(String fileName) {
        ClassLoader classLoader = Resources.class.getClassLoader();

        URL resource = classLoader.getResource(fileName);

        if (resource == null) throw new AssertionError();
        return resource.getProtocol() + ":" + resource.getPath();
    }
}

package pl.sewerynkamil.board;

import java.net.URL;

/**
 * Author Kamil Seweryn
 */

public class Resources {

    public static String getPath(String fileName) {
        ClassLoader classLoader = Resources.class.getClassLoader();

        URL resource = classLoader.getResource(fileName);

        if (resource == null) throw new AssertionError();
        return resource.getProtocol() + ":" + resource.getPath();
    }
}

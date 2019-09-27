package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIoUtils {
    public static byte[] loadFileFromClasspath(String filePath) throws IOException, URISyntaxException {
        URL url = FileIoUtils.class.getClassLoader().getResource(filePath);
        if (url == null) {
            throw new FileNotFoundException();
        }
        Path path = Paths.get(url.toURI());
        return Files.readAllBytes(path);
    }
}

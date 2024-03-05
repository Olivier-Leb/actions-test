package example.github.actions.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    public static String getConfigValue(final String property) {
        Properties properties = new Properties();

        try (InputStream propFileInpStream = PropertyLoader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {

            properties.load(propFileInpStream);

            String p = properties.getProperty(property);

            return (p != null ? p : "");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration from config.properties");
        }
    }
}

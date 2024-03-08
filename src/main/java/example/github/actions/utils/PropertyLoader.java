package example.github.actions.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    /**
     * Return a property value based on environment variable or the 'config.properties' file.
     * Ex: To have access to the property 'driver.default', either:
     *   set an environment variable 'ENV_DRIVER_DEFAULT', or
     *   set the value of 'driver.default' in resources/config.properties
     *
     * @param property: property name to find, if an environment variable of same name is defined, will be returned first
     * @return String the property value
     */
    public static String getConfigValue(final String property) {
        // Search in environment variable
        String envValue = System.getenv("ENV_" + property.toUpperCase().replace(".", "_"));
        if (envValue != null) {
            return envValue;
        }

        // Fallback to the default .properties
        Properties properties = new Properties();

        try (InputStream propFileInpStream = PropertyLoader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {

            properties.load(propFileInpStream);

            String value = properties.getProperty(property);

            return (value != null ? value : "");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration from config.properties");
        }
    }
}

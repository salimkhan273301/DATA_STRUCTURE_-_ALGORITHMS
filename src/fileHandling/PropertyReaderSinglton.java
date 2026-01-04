package fileHandling;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReaderSinglton {
    private static final String PROPERTIES_FILE_PATH = "config.properties";
    private Properties properties;

    private PropertyReaderSinglton() {
        properties = loadProperties();
    }

    private Properties loadProperties() {
        Properties loadedProperties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH)) {
            loadedProperties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedProperties;
    }

    public static PropertyReaderSinglton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public int getIntProperty(String key) {
        String value = getProperty(key);
        return value != null ? Integer.parseInt(value) : 0; // Default value if property is not found
    }

    private static class SingletonHelper {
        private static final PropertyReaderSinglton INSTANCE = new PropertyReaderSinglton();
    }
}

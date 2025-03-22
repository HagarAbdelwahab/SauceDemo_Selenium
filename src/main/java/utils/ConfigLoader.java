package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();
    private static ConfigLoader instance = null;
    private static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);

    private ConfigLoader() {
        loadProperties();
    }

    public static ConfigLoader getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }

    private void loadProperties() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new RuntimeException("config.properties not found in classpath");
            }
        } catch (IOException e) {
            logger.error("Error loading properties file", e);
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException(key + " not specified in config.properties file");
        }
        return value;
    }

    public String getBaseUrl() {
        return getProperty("base.url");
    }

    public String getBrowser() {
        return getProperty("browser");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless"));
    }

    public int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait"));
    }

    public int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait"));
    }

    public int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("page.load.timeout"));
    }
}
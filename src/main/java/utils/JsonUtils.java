package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static <T> T loadJsonFile(String filePath, Class<T> valueType) {
        try (InputStream inputStream = JsonUtils.class.getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new RuntimeException("File not found: " + filePath);
            }
            return objectMapper.readValue(inputStream, valueType);
        } catch (IOException e) {
            logger.error("Error reading JSON file: {}", filePath, e);
            throw new RuntimeException("Failed to parse JSON file: " + filePath, e);
        }
    }

    public static <T> T loadJsonFromFile(File file, Class<T> valueType) {
        try {
            return objectMapper.readValue(file, valueType);
        } catch (IOException e) {
            logger.error("Error reading JSON file: {}", file.getName(), e);
            throw new RuntimeException("Failed to parse JSON file: " + file.getName(), e);
        }
    }

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            logger.error("Error converting object to JSON string", e);
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }
}
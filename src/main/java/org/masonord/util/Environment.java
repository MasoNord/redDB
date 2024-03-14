package org.masonord.util;

import org.masonord.exception.PropertyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Environment {
    private static final Logger LOGGER = LoggerFactory.getLogger(Environment.class);
    private final Properties properties;

    public Environment(String fileName) throws FileNotFoundException {
        this.properties = new Properties();
        load(fileName);
    }

    private void load(String fileName) throws FileNotFoundException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream stream = loader.getResourceAsStream(fileName)) {
            properties.load(stream);
        }catch (IOException e) {
            LOGGER.error("File not found: " + fileName);
            throw new FileNotFoundException("File not found by the given name, try another one");
        }
    }

    public String getValue(String key) throws PropertyNotFoundException {
        if (!properties.containsKey(key)) {
            LOGGER.error("Property not found: " + key);
            throw new PropertyNotFoundException("Property not found");
        }
        return properties.getProperty(key);
    }
}

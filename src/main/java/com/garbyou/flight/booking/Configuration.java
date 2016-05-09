package com.garbyou.flight.booking;

import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Contains main configuration
 */
@Singleton
public class Configuration {
    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

    /**
     * Contains configuration properties
     */
    private static Properties properties = new Properties();

    /**
     * Loading statically in order to obtain properties before guice injection
     */
    static {
        logger.debug("Loading config.properties files");
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            logger.debug("Configuration loaded");
        } catch (IOException e) {
            logger.error("Unable to load config file", e);
        }
    }

    /**
     * Get test resources location
     *
     * @return path to retrieve resources in test
     */
    public static String getTestResourcesPath() {
        return properties.getProperty("resources.location");
    }

}
package com.garbyou.flight.booking.config;

import com.google.inject.Inject;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Jersey configuration for prepare resources
 */
public class JerseyAppConfig extends ResourceConfig {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(JerseyAppConfig.class);

    @Inject
    JerseyAppConfig() {
        logger.debug("configure jersey");

        packages("fr.invissys.nemo.web");
    }
}
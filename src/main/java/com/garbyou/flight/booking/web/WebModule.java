package com.garbyou.flight.booking.web;

import com.garbyou.flight.booking.web.api.FlightResource;
import com.google.inject.AbstractModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebModule extends AbstractModule {

    private static final Logger logger = LoggerFactory.getLogger(WebModule.class);

    @Override
    protected void configure() {
        logger.debug("Web module begin configuration");

        bind(FlightResource.class);

        logger.debug("Web module configuration completed");
    }
}

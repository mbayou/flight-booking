package com.garbyou.flight.booking.web;

import com.garbyou.flight.booking.web.api.BookingResource;
import com.garbyou.flight.booking.web.api.FlightResource;
import com.garbyou.flight.booking.web.mappers.IllegalArgumentExceptionMapper;
import com.google.inject.AbstractModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Web module
 */
public class WebModule extends AbstractModule {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(WebModule.class);

    @Override
    protected void configure() {
        logger.debug("Web module begin configuration");

        bind(IllegalArgumentExceptionMapper.class);
        bind(FlightResource.class);
        bind(BookingResource.class);

        logger.debug("Web module configuration completed");
    }
}

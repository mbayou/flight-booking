package com.garbyou.flight.booking.config;

import com.garbyou.flight.booking.persistence.BookingDAO;
import com.garbyou.flight.booking.persistence.FlightDAO;
import com.garbyou.flight.booking.persistence.SeatDAO;
import com.garbyou.flight.booking.persistence.impl.BookingDAOImpl;
import com.garbyou.flight.booking.persistence.impl.FlightDAOImpl;
import com.garbyou.flight.booking.persistence.impl.SeatDAOImpl;
import com.garbyou.flight.booking.service.command.BookingCommandService;
import com.garbyou.flight.booking.service.command.FlightCommandService;
import com.garbyou.flight.booking.service.command.impl.BookingCommandServiceImpl;
import com.garbyou.flight.booking.service.command.impl.FlightCommandServiceImpl;
import com.garbyou.flight.booking.service.query.BookingQueryService;
import com.garbyou.flight.booking.service.query.FlightQueryService;
import com.garbyou.flight.booking.service.query.impl.BookingQueryServiceImpl;
import com.garbyou.flight.booking.service.query.impl.FlightQueryServiceImpl;
import com.garbyou.flight.booking.web.WebModule;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Scopes;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main module (all of dependency injection is here)
 */
public class MainModule extends AbstractModule {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(MainModule.class);

    @Override
    protected void configure() {

        logger.debug("Starting configuration");

        try {
            install(new WebModule());
            bind(ServletContainer.class).toProvider(ServletContainerProvider.class).in(Scopes.SINGLETON);
            bind(JerseyAppConfig.class);

            install(new ServletModule() {
                @Override
                protected void configureServlets() {
                    filter("/*").through(ServletContainer.class);
                    install(new JpaPersistModule("flightbooking"));
                    filter("/*").through(PersistFilter.class);
                }
            });

            bind(PersistFilter.class);

            this.bindDaos();
            this.bindServices();

            logger.debug("Configuration done");

        } catch (Exception e) {
            logger.error("Error during configuration", e);
            throw e;
        }


    }

    /**
     * Performs dao bindings
     */
    private void bindDaos() {
        bind(FlightDAO.class).to(FlightDAOImpl.class);
        bind(BookingDAO.class).to(BookingDAOImpl.class);
        bind(SeatDAO.class).to(SeatDAOImpl.class);
    }

    /**
     * Performs service bindings
     */
    private void bindServices() {
         bind(FlightQueryService.class).to(FlightQueryServiceImpl.class);
         bind(FlightCommandService.class).to(FlightCommandServiceImpl.class);
         bind(BookingCommandService.class).to(BookingCommandServiceImpl.class);
         bind(BookingQueryService.class).to(BookingQueryServiceImpl.class);
    }

    /**
     * Servlet container, with all resources
     */
    private static class ServletContainerProvider implements com.google.inject.Provider<ServletContainer> {

        /**
         * Jersey app with resources
         */
        private final JerseyAppConfig app;

        /**
         * Setter for jersey app
         *
         * @param app new jersey app
         */
        @Inject
        ServletContainerProvider(final JerseyAppConfig app) {
            this.app = app;
        }

        @Override
        public ServletContainer get() {
            return new ServletContainer(app);
        }
    }
}

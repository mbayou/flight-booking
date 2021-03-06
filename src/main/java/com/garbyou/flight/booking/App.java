package com.garbyou.flight.booking;

import com.garbyou.flight.booking.config.MainModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.squarespace.jersey2.guice.BootstrapModule;
import com.squarespace.jersey2.guice.BootstrapUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.glassfish.hk2.api.ServiceLocator;
import org.slf4j.bridge.SLF4JBridgeHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.logging.LogManager;

/**
 * Application class
 */
public final class App {

    /**
     * Default constructor in private
     * Because this class is utils never instantiated
     */
    private App() {
    }

    /**
     * Main (entry point)
     *
     * @param args argument pass to application
     *
     * @throws Exception when an error occurred
     */
    public static void main(final String[] args) throws Exception {
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();


        ServiceLocator serviceLocator = BootstrapUtils.newServiceLocator();
        Injector injector = Guice.createInjector(new ServiceModule(), new BootstrapModule(serviceLocator));

        BootstrapUtils.link(serviceLocator, injector);
        BootstrapUtils.install(serviceLocator);


        Server server = new Server(9888);

        ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        servletContextHandler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));

        HandlerCollection handlerCollection = new HandlerCollection();
        handlerCollection.addHandler(servletContextHandler);

        server.setHandler(handlerCollection);


        // You MUST add DefaultServlet or your server will always return 404s,  needed to satisfy servlet spe
        servletContextHandler.addServlet(DefaultServlet.class, "/");

        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }

    /**
     * Service module, use for injection
     */
    private static class ServiceModule extends AbstractModule {

        @Override
        protected void configure() {
            binder().requireExplicitBindings();
            install(new MainModule());
        }
    }
}

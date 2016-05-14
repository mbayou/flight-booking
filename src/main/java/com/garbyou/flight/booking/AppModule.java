package com.garbyou.flight.booking;

import com.garbyou.flight.booking.config.MainModule;
import com.google.inject.AbstractModule;
import com.palominolabs.http.server.HttpServerWrapperModule;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        binder().requireExplicitBindings();
        install(new HttpServerWrapperModule());
        install(new MainModule());
    }
}

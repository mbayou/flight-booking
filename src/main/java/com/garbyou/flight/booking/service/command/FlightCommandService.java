package com.garbyou.flight.booking.service.command;

import java.io.FileNotFoundException;

/**
 * Contains method to perform flight command
 */
public interface FlightCommandService {

    /**
     * Prepare data application
     * @throws FileNotFoundException
     */
    void prepare() throws FileNotFoundException;
}

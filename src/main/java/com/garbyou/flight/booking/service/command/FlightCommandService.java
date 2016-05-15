package com.garbyou.flight.booking.service.command;

import java.io.FileNotFoundException;

/**
 * Contains method to perform flight command
 */
public interface FlightCommandService {

    /**
     * Prepare data application
     * @throws FileNotFoundException thrown when iatadb is not found
     */
    void prepare() throws FileNotFoundException;
}

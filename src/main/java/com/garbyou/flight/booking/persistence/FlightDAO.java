package com.garbyou.flight.booking.persistence;

import com.garbyou.flight.booking.common.FindFlightQuery;
import com.garbyou.flight.booking.persistence.domain.Flight;

import java.util.List;

/**
 * Data Access Object of {@link com.garbyou.flight.booking.persistence.domain.Flight}
 */
public interface FlightDAO {

    /**
     * Saves flight
     *
     * @param flight flight to save
     */
    void saveOrUpdate(Flight flight);

    /**
     * Retrieves flight by a specific query
     *
     * @param query query to retrieves flight
     *
     * @return List of flights
     */
    List<Flight> findFlightByQuery(FindFlightQuery query);

    /**
     * Get flight by ID
     *
     * @param id flight identifier
     *
     * @return Flight
     */
    Flight findById(final int id);
}

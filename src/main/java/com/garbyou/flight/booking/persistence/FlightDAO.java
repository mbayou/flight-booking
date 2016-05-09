package com.garbyou.flight.booking.persistence;

import com.garbyou.flight.booking.persistence.domain.Flight;

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
}

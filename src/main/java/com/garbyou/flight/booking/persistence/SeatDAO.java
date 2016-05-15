package com.garbyou.flight.booking.persistence;

import com.garbyou.flight.booking.persistence.domain.Seat;

/**
 * Data Access Object of {@link com.garbyou.flight.booking.persistence.domain.Seat}
 */
public interface SeatDAO {

    /**
     * Saves seat
     *
     * @param seat seat to save
     */
    void saveOrUpdate(Seat seat);

}

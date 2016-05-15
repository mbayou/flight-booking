package com.garbyou.flight.booking.persistence;

import com.garbyou.flight.booking.persistence.domain.Booking;

/**
 * Data Access Object of {@link Booking}
 */
public interface BookingDAO {

    /**
     * Saves booking
     *
     * @param booking booking to save
     */
    void saveOrUpdate(Booking booking);

    /**
     * Get booking by ID
     *
     * @param id booking identifier
     *
     * @return booking
     */
    Booking findById(final int id);

    /**
     * Delete booking
     * @param booking booking to delete
     */
    void delete(final Booking booking);
}

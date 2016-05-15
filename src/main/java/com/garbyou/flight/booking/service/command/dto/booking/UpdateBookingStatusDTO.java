package com.garbyou.flight.booking.service.command.dto.booking;

import com.garbyou.flight.booking.common.BookingStatus;

/**
 * Contain element to update booking status
 */
public class UpdateBookingStatusDTO {

    /**
     * Booking identifier
     */
    private int id;

    /**
     * Booking status
     */
    private BookingStatus status;

    /**
     * Sets new status
     *
     * @param status new value of status.
     */
    public void setStatus(final BookingStatus status) {
        this.status = status;
    }

    /**
     * Gets status
     *
     * @return status
     */
    public BookingStatus getStatus() {
        return status;
    }

    /**
     * Gets id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets new id
     *
     * @param id new value of id.
     */
    public void setId(final int id) {
        this.id = id;
    }
}

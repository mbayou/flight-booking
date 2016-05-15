package com.garbyou.flight.booking.web.api.model.booking;

import com.garbyou.flight.booking.common.BookingStatus;

/**
 * Contain element to update booking status
 */
public class UpdateBookingStatus {

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
}

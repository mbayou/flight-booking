package com.garbyou.flight.booking.service.query;

import com.garbyou.flight.booking.service.query.dto.booking.BookingDTO;

/**
 * Contains method to perform booking query
 */
public interface BookingQueryService {

    /**
     * Get a specific booking information
     *
     * @param bookingId booking identifier
     *
     * @return booking detail
     */
    BookingDTO getBooking(int bookingId);
}

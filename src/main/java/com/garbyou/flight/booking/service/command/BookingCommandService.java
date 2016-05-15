package com.garbyou.flight.booking.service.command;

import com.garbyou.flight.booking.service.command.dto.SaveBookingDTO;

/**
 * Contains method to perform booking command
 */
public interface BookingCommandService {

    /**
     * Create a booking from command
     *
     * @param command command use to create booking
     *
     * @return identifier of created booking
     */
    long createBooking(SaveBookingDTO command);
}

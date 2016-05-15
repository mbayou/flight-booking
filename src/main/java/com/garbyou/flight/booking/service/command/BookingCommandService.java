package com.garbyou.flight.booking.service.command;

import com.garbyou.flight.booking.service.command.dto.booking.AddBookingSeatDTO;
import com.garbyou.flight.booking.service.command.dto.booking.SaveBookingDTO;
import com.garbyou.flight.booking.service.command.dto.booking.UpdateBookingStatusDTO;

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

    /**
     * Delete booking
     *
     * @param bookingId booking identifier
     */
    void deleteBooking(int bookingId);

    /**
     * Update booking status
     *
     * @param command command to update status
     */
    void updateBookingStatus(UpdateBookingStatusDTO command);

    /**
     * Add seat to booking
     * @param command command use to add seat to booking
     */
    void addBookingSeat(AddBookingSeatDTO command);
}

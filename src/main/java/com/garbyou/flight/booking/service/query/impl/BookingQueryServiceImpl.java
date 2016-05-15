package com.garbyou.flight.booking.service.query.impl;

import com.garbyou.flight.booking.persistence.BookingDAO;
import com.garbyou.flight.booking.persistence.domain.Booking;
import com.garbyou.flight.booking.service.query.BookingQueryService;
import com.garbyou.flight.booking.service.query.dto.booking.BookingDTO;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link BookingQueryService}
 */
@Singleton
public class BookingQueryServiceImpl implements BookingQueryService{

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(BookingQueryServiceImpl.class);


    /**
     * Booking dao
     */
    @Inject
    private BookingDAO bookingDAO;

    @Override
    public BookingDTO getBooking(int bookingId) {
        logger.debug("Get Booking");

        Booking booking = this.bookingDAO.findById(bookingId);
        if (booking == null){
            return null;
        }

        BookingDTO dto = new BookingDTO();

        dto.setId(booking.getId());
        // Booking need create at least one seat
        dto.setFlightId(booking.getSeats().get(0).getFlight().getId());
        dto.setCabinClass(booking.getSeats().get(0).getCabinClass());
        dto.setPrices((booking.getSeats().get(0).getPriceWithoutTaxes() + booking.getSeats().get(0).getTaxesPrice()) * booking.getSeats().size());
        dto.setQuantity(booking.getSeats().size());
        dto.setStatus(booking.getStatus());
        dto.setCustomer(new BookingDTO.Customer());
        dto.getCustomer().setFirstName(booking.getCustomer().getFirstName());
        dto.getCustomer().setLastName(booking.getCustomer().getLastName());

        return dto;
    }
}

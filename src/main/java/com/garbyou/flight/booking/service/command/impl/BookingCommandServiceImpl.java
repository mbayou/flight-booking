package com.garbyou.flight.booking.service.command.impl;

import com.garbyou.flight.booking.common.BookingStatus;
import com.garbyou.flight.booking.persistence.BookingDAO;
import com.garbyou.flight.booking.persistence.FlightDAO;
import com.garbyou.flight.booking.persistence.SeatDAO;
import com.garbyou.flight.booking.persistence.domain.Booking;
import com.garbyou.flight.booking.persistence.domain.Flight;
import com.garbyou.flight.booking.persistence.domain.Seat;
import com.garbyou.flight.booking.service.command.BookingCommandService;
import com.garbyou.flight.booking.service.command.dto.SaveBookingDTO;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of {@link BookingCommandService}
 */
@Singleton
public class BookingCommandServiceImpl implements BookingCommandService {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(BookingCommandServiceImpl.class);

    /**
     * Flight dao
     */
    @Inject
    private FlightDAO flightDao;

    /**
     * Booking dao
     */
    @Inject
    private BookingDAO bookingDao;

    /**
     * Seat dao
     */
    @Inject
    private SeatDAO seatDao;


    @Override
    public long createBooking(final SaveBookingDTO command) {
        logger.debug("Create a booking");
        Preconditions.checkArgument(command.getCabinClass() != null, "Cabin class cannot be null");

        final Flight flight = this.flightDao.findById(command.getFlightId());
        Preconditions.checkArgument(flight != null, "Flight cannot be find");
        final List<Seat> bookedSeat = new ArrayList<>();
        final List<Seat> bookableSeats = new ArrayList<>();
        switch (command.getCabinClass()) {
            case Y:
                for (Seat seat : flight.getYSeat()){
                    if (seat.getBooking() == null){
                        bookableSeats.add(seat);
                    }
                }
                Preconditions.checkArgument(bookableSeats.size() >= command.getQuantity(), "Not enough bookable seat for the booking");
                for (int i = 0; i < command.getQuantity(); i++) {
                    bookedSeat.add(bookableSeats.get(i));
                }
                break;
            case J:
                for (Seat seat : flight.getJSeat()){
                    if (seat.getBooking() == null){
                        bookableSeats.add(seat);
                    }
                }
                Preconditions.checkArgument(bookableSeats.size() >= command.getQuantity(), "Not enough bookable seat for the booking");
                for (int i = 0; i < command.getQuantity(); i++) {
                    bookedSeat.add(bookableSeats.get(i));
                }
                break;
            case M:
                for (Seat seat : flight.getMSeat()){
                    if (seat.getBooking() == null){
                        bookableSeats.add(seat);
                    }
                }
                Preconditions.checkArgument(bookableSeats.size() >= command.getQuantity(), "Not enough bookable seat for the booking");
                for (int i = 0; i < command.getQuantity(); i++) {
                    bookedSeat.add(bookableSeats.get(i));
                }
                break;
        }

        Booking booking = new Booking();
        booking.setSeats(bookedSeat);
        booking.setStatus(BookingStatus.PENDING_CONFIRMATION);

        this.bookingDao.saveOrUpdate(booking);

        for (Seat seat : bookedSeat){
            seat.setBooking(booking);
            this.seatDao.saveOrUpdate(seat);
        }

        return booking.getId();
    }
}

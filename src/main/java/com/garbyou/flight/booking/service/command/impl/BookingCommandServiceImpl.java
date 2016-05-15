package com.garbyou.flight.booking.service.command.impl;

import com.garbyou.flight.booking.common.BookingStatus;
import com.garbyou.flight.booking.common.CabinClass;
import com.garbyou.flight.booking.persistence.BookingDAO;
import com.garbyou.flight.booking.persistence.FlightDAO;
import com.garbyou.flight.booking.persistence.SeatDAO;
import com.garbyou.flight.booking.persistence.domain.Booking;
import com.garbyou.flight.booking.persistence.domain.Customer;
import com.garbyou.flight.booking.persistence.domain.Flight;
import com.garbyou.flight.booking.persistence.domain.Seat;
import com.garbyou.flight.booking.service.command.BookingCommandService;
import com.garbyou.flight.booking.service.command.dto.booking.AddBookingSeatDTO;
import com.garbyou.flight.booking.service.command.dto.booking.SaveBookingDTO;
import com.garbyou.flight.booking.service.command.dto.booking.UpdateBookingStatusDTO;
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
     * BookingDTO dao
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
        Preconditions.checkArgument(command.getQuantity() > 0, "Quantity cannot be inferior to 1");

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
        booking.setCustomer(new Customer());
        booking.getCustomer().setFirstName(command.getCustomer().getFirstName());
        booking.getCustomer().setLastName(command.getCustomer().getLastName());

        this.bookingDao.saveOrUpdate(booking);

        for (Seat seat : bookedSeat){
            seat.setBooking(booking);
            this.seatDao.saveOrUpdate(seat);
        }

        return booking.getId();
    }

    @Override
    public void deleteBooking(int bookingId) {
        logger.debug("Deletion of booking '{}'", bookingId);
        Booking booking = this.bookingDao.findById(bookingId);
        Preconditions.checkArgument(booking != null, "Booking cannot be find");
        for (Seat seat : booking.getSeats()){
            seat.setBooking(null);
            this.seatDao.saveOrUpdate(seat);
        }

        this.bookingDao.delete(booking);
        logger.debug("Booking deleted");
    }

    @Override
    public void updateBookingStatus(UpdateBookingStatusDTO command) {
        Preconditions.checkArgument(command != null, "Command cannot be null");
        Preconditions.checkArgument(command.getStatus() != null, "Command status cannot be null");

        logger.debug("Update status of booking '{}'", command.getId());

        Booking booking = this.bookingDao.findById(command.getId());
        Preconditions.checkArgument(booking != null, "Booking cannot be find");

        booking.setStatus(command.getStatus());

        this.bookingDao.saveOrUpdate(booking);

    }

    @Override
    public void addBookingSeat(AddBookingSeatDTO command) {
        Preconditions.checkArgument(command != null, "Command cannot be null");

        logger.debug("Add seat to booking '{}'", command.getId());

        Booking booking = this.bookingDao.findById(command.getId());
        Preconditions.checkArgument(booking != null, "Booking cannot be find");

        // Booking is created at least one seat
        CabinClass cabinClass = booking.getSeats().get(0).getCabinClass();
        Flight flight = booking.getSeats().get(0).getFlight();
        final List<Seat> bookedSeat = new ArrayList<>();
        final List<Seat> bookableSeats = new ArrayList<>();
        switch (cabinClass) {
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
        booking.getSeats().addAll(bookedSeat);

        for (Seat seat : bookedSeat){
            seat.setBooking(booking);
            this.seatDao.saveOrUpdate(seat);
        }

        this.bookingDao.saveOrUpdate(booking);
    }
}

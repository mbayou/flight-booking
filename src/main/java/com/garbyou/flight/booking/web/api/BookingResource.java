package com.garbyou.flight.booking.web.api;

import com.garbyou.flight.booking.service.command.BookingCommandService;
import com.garbyou.flight.booking.service.command.dto.booking.AddBookingSeatDTO;
import com.garbyou.flight.booking.service.command.dto.booking.SaveBookingDTO;
import com.garbyou.flight.booking.service.command.dto.booking.UpdateBookingStatusDTO;
import com.garbyou.flight.booking.service.query.BookingQueryService;
import com.garbyou.flight.booking.service.query.dto.booking.BookingDTO;
import com.garbyou.flight.booking.web.api.model.LongIdentifier;
import com.garbyou.flight.booking.web.api.model.booking.AddBookingSeat;
import com.garbyou.flight.booking.web.api.model.booking.Booking;
import com.garbyou.flight.booking.web.api.model.booking.SaveBooking;
import com.garbyou.flight.booking.web.api.model.booking.UpdateBookingStatus;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.google.inject.servlet.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Web access to booking services
 */
@Path("/bookings")
@RequestScoped
public class BookingResource {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(BookingResource.class);

    /**
     * BookingDTO command service
     */
    @Inject
    private BookingCommandService bookingCommandService;

    /**
     * BookingDTO query service
     */
    @Inject
    private BookingQueryService bookingQueryService;

    /**
     * Create a booking
     *
     * @param booking booking to create
     *
     * @return identifier of created booking
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    @Transactional
    public LongIdentifier createBooking(final SaveBooking booking){
        logger.debug("Save new booking");
        Preconditions.checkArgument(booking != null, "Booking cannot be null");
        Preconditions.checkArgument(booking.getCustomer() != null, "Booking customer cannot be null");

        SaveBookingDTO command = new SaveBookingDTO();
        command.setFlightId(booking.getFlightId());
        command.setCabinClass(booking.getCabinClass());
        command.setQuantity(booking.getQuantity());
        command.setCustomer(new SaveBookingDTO.Customer());
        command.getCustomer().setFirstName(booking.getCustomer().getFirstName());
        command.getCustomer().setLastName(booking.getCustomer().getLastName());

        return new LongIdentifier(this.bookingCommandService.createBooking(command));
    }

    /**
     * Get booking
     *
     * @param bookingId booking identifier
     *
     * @return BookingDTO detail
     */
    @GET
    @Path("/{bookingid}")
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public Booking getBooking(@PathParam("bookingid") final int bookingId) {
        logger.debug("Get booking detail");

        BookingDTO dto = this.bookingQueryService.getBooking(bookingId);
        if (dto == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        Booking booking = new Booking();
        booking.setId(dto.getId());
        booking.setQuantity(dto.getQuantity());
        booking.setStatus(dto.getStatus());
        booking.setFlightId(dto.getFlightId());
        booking.setPrices(dto.getPrices());
        booking.setCabinClass(dto.getCabinClass());
        booking.setCustomer(new Booking.Customer());
        booking.getCustomer().setFirstName(dto.getCustomer().getFirstName());
        booking.getCustomer().setLastName(dto.getCustomer().getLastName());

        return booking;
    }

    /**
     * Delete booking
     *
     * @param bookingId booking identifier
     */
    @DELETE
    @Path("/{bookingid}")
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    @Transactional
    public void deleteBooking(@PathParam("bookingid") final int bookingId) {
        logger.debug("Delete booking detail");
        BookingDTO dto = this.bookingQueryService.getBooking(bookingId);
        if (dto == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        this.bookingCommandService.deleteBooking(bookingId);
    }

    /**
     * Update booking status
     * @param bookingId booking identifier
     * @param updateBookingStatus new booking status
     */
    @PUT
    @Path("/{bookingid}/status")
    @Consumes({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    @Transactional
    public void updateBookingStatus(@PathParam("bookingid") final int bookingId, final UpdateBookingStatus updateBookingStatus){
        logger.debug("Save new booking");
        Preconditions.checkArgument(updateBookingStatus != null, "Booking status cannot be null");

        BookingDTO dto = this.bookingQueryService.getBooking(bookingId);
        if (dto == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        UpdateBookingStatusDTO command = new UpdateBookingStatusDTO();
        command.setStatus(updateBookingStatus.getStatus());
        command.setId(bookingId);

        this.bookingCommandService.updateBookingStatus(command);
    }

    /**
     * Add booking seat
     * @param bookingId booking identifier
     * @param addBookingSeat booking seat quantity to add
     */
    @POST
    @Path("/{bookingid}/seats")
    @Consumes({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    @Transactional
    public void addBookingSeat(@PathParam("bookingid") final int bookingId, final AddBookingSeat addBookingSeat){
        logger.debug("Save new booking");
        Preconditions.checkArgument(addBookingSeat != null, "Booking seat cannot be null");

        BookingDTO dto = this.bookingQueryService.getBooking(bookingId);
        if (dto == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        AddBookingSeatDTO command = new AddBookingSeatDTO();
        command.setQuantity(addBookingSeat.getQuantity());
        command.setId(bookingId);

        this.bookingCommandService.addBookingSeat(command);
    }
}

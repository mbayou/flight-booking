package com.garbyou.flight.booking.web.api;

import com.garbyou.flight.booking.service.command.BookingCommandService;
import com.garbyou.flight.booking.service.command.dto.SaveBookingDTO;
import com.garbyou.flight.booking.service.query.FlightQueryService;
import com.garbyou.flight.booking.web.api.model.LongIdentifier;
import com.garbyou.flight.booking.web.api.model.booking.SaveBooking;
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
     * Flight query service
     */
    @Inject
    private FlightQueryService flightQueryService;

    /**
     * Booking command service
     */
    @Inject
    private BookingCommandService bookingCommandService;


    @POST
    @Consumes({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    @Transactional
    public LongIdentifier createBooking(final SaveBooking booking){
        logger.debug("Save new booking");

        if (this.flightQueryService.getFlight(booking.getFlightId()) == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        SaveBookingDTO command = new SaveBookingDTO();
        command.setFlightId(booking.getFlightId());
        command.setCabinClass(booking.getCabinClass());
        command.setQuantity(booking.getQuantity());

        return new LongIdentifier(this.bookingCommandService.createBooking(command));
    }
}

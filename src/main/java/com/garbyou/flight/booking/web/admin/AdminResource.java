package com.garbyou.flight.booking.web.admin;

import com.garbyou.flight.booking.service.command.FlightCommandService;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.google.inject.servlet.RequestScoped;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.FileNotFoundException;

/**
 * Web access to admin services
 */
@Path("/admin")
@RequestScoped
public class AdminResource {

    /**
     * Flight command service
     */
    @Inject
    private FlightCommandService flightCommandService;

    /**
     * Prepare data for application
     * @throws FileNotFoundException Thrown when iatadb is not found
     */
    @POST
    @Path("/prepare")
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    @Transactional
    public void prepareData() throws FileNotFoundException {
        this.flightCommandService.prepare();
    }
}

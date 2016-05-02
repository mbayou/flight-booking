package com.garbyou.flight.booking.web.api;

import com.google.inject.persist.Transactional;
import com.google.inject.servlet.RequestScoped;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Web access to flight from services
 */
@Path("/flights")
@RequestScoped
public class FlightResource {

    /**
     * Create flight
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    @Consumes({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    @Transactional
    public long parseContactCSV() {
        return 12;
    }
}

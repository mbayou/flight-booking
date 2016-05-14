package com.garbyou.flight.booking.web.api;

import com.garbyou.flight.booking.common.FindFlightQuery;
import com.garbyou.flight.booking.service.query.FlightQueryService;
import com.garbyou.flight.booking.service.query.dto.FlightDTO;
import com.garbyou.flight.booking.web.api.model.Flight;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.google.inject.servlet.RequestScoped;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Web access to flight from services
 */
@Path("/flights")
@RequestScoped
public class FlightResource {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(FlightResource.class);

    /**
     * Flight query service
     */
    @Inject
    private FlightQueryService flightQueryService;

    /**
     * Retrieve flight for a specific query
     *
     * @param arrivalAirportCode arrival airport code
     * @param departureAirportCode departure airport cide
     * @param arrivalDate arrival date
     * @param departureDate departure date
     * @param flightDay flight day
     * @return List of flight
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    @Transactional
    public List<Flight> findFlights(@QueryParam("arrival") final String arrivalAirportCode,
                                    @QueryParam("departure") final String departureAirportCode,
                                    @QueryParam("arrivaldate") final String arrivalDate,
                                    @QueryParam("departuredate") final String departureDate,
                                    @QueryParam("flightday") final String flightDay) {
        logger.debug("Get for flights");

        final FindFlightQuery query = new FindFlightQuery();
        query.setDepartureAirportCode(departureAirportCode);
        query.setArrivalAirportCode(arrivalAirportCode);
        if (!Strings.isNullOrEmpty(arrivalDate)){
            query.setArrivalDate(ISODateTimeFormat.dateTimeNoMillis().withZoneUTC().parseMillis(arrivalDate));
        }
        if (!Strings.isNullOrEmpty(departureDate)){
            query.setDepartureDate(ISODateTimeFormat.dateTimeNoMillis().withZoneUTC().parseMillis(departureDate));
        }
        if (!Strings.isNullOrEmpty(flightDay)){
            query.setFlightDate(ISODateTimeFormat.dateTimeNoMillis().withZoneUTC().parseMillis(flightDay));
        }

        List<FlightDTO> dtos = this.flightQueryService.findFlight(query);
        List<Flight> flights = new ArrayList<>();

        for (FlightDTO dto : dtos){
            Flight flight = new Flight();
            flight.setAirLine(dto.getAirLine());
            flight.setId(dto.getId());
            flight.getJSeatCabinInformation().setQuantity(dto.getJSeatCabinInformation().getQuantity());
            flight.getYSeatCabinInformation().setQuantity(dto.getYSeatCabinInformation().getQuantity());
            flight.getMSeatCabinInformation().setQuantity(dto.getMSeatCabinInformation().getQuantity());
            flight.setDepartureAirportCode(dto.getDepartureAirportCode());
            flight.setArrivalAirportCode(dto.getArrivalAirportCode());
            flight.setArrivalDate(ISODateTimeFormat.dateTimeNoMillis().withZoneUTC().print(dto.getArrivalDate()));
            flight.setDepartureDate(ISODateTimeFormat.dateTimeNoMillis().withZoneUTC().print(dto.getDepartureDate()));

            flights.add(flight);
        }

        return flights;
    }
}

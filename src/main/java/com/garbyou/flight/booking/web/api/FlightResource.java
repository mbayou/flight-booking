package com.garbyou.flight.booking.web.api;

import com.garbyou.flight.booking.common.FindFlightQuery;
import com.garbyou.flight.booking.service.query.FlightQueryService;
import com.garbyou.flight.booking.service.query.dto.flight.FlightDTO;
import com.garbyou.flight.booking.service.query.dto.flight.FlightDetailDTO;
import com.garbyou.flight.booking.web.api.model.flight.Flight;
import com.garbyou.flight.booking.web.api.model.flight.FlightDetail;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.google.inject.servlet.RequestScoped;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
            query.setFlightDate(ISODateTimeFormat.date().withZoneUTC().parseMillis(flightDay));
        }

        List<FlightDTO> dtos = this.flightQueryService.findFlights(query);
        List<Flight> flights = new ArrayList<>();

        for (FlightDTO dto : dtos){
            Flight flight = new Flight();
            flight.setAirLine(dto.getAirLine());
            flight.setId(dto.getId());
            flight.setFlightId(dto.getFlightId());
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

    /**
     * Get Specific flight
     *
     * @param flightId flight identifier in database
     *
     * @return List of flight
     */
    @GET
    @Path("/{flightid}")
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    @Transactional
    public FlightDetail findFlights(@PathParam("flightid") final int flightId) {
        logger.debug("Get flight detail");

        FlightDetailDTO dto = this.flightQueryService.getFlight(flightId);
        if (dto == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        FlightDetail flight = new FlightDetail();
        flight.setAirLine(dto.getAirLine());
        flight.setId(dto.getId());
        flight.setFlightId(dto.getFlightId());
        flight.getJSeatCabinInformation().setQuantity(dto.getJSeatCabinInformation().getQuantity());
        flight.getJSeatCabinInformation().setPriceWithoutTaxes(dto.getJSeatCabinInformation().getPriceWithoutTaxes());
        flight.getJSeatCabinInformation().setTaxesPrice(dto.getJSeatCabinInformation().getTaxesPrice());
        flight.getYSeatCabinInformation().setQuantity(dto.getYSeatCabinInformation().getQuantity());
        flight.getYSeatCabinInformation().setPriceWithoutTaxes(dto.getYSeatCabinInformation().getPriceWithoutTaxes());
        flight.getYSeatCabinInformation().setTaxesPrice(dto.getYSeatCabinInformation().getTaxesPrice());
        flight.getMSeatCabinInformation().setQuantity(dto.getMSeatCabinInformation().getQuantity());
        flight.getMSeatCabinInformation().setPriceWithoutTaxes(dto.getMSeatCabinInformation().getPriceWithoutTaxes());
        flight.getMSeatCabinInformation().setTaxesPrice(dto.getMSeatCabinInformation().getTaxesPrice());
        flight.setDepartureAirportCode(dto.getDepartureAirportCode());
        flight.setArrivalAirportCode(dto.getArrivalAirportCode());
        flight.setArrivalDate(ISODateTimeFormat.dateTimeNoMillis().withZoneUTC().print(dto.getArrivalDate()));
        flight.setDepartureDate(ISODateTimeFormat.dateTimeNoMillis().withZoneUTC().print(dto.getDepartureDate()));


        return flight;
    }
}

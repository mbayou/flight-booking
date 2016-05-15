package com.garbyou.flight.booking.service.query;

import com.garbyou.flight.booking.common.FindFlightQuery;
import com.garbyou.flight.booking.service.query.dto.flight.FlightDTO;
import com.garbyou.flight.booking.service.query.dto.flight.FlightDetailDTO;

import java.util.List;

/**
 * Contains method to perform flight query
 */
public interface FlightQueryService {

    /**
     * Retrieves flights with specific query
     *
     * @param query specific query
     *
     * @return list of flight
     */
    List<FlightDTO> findFlights(FindFlightQuery query);

    /**
     * Get a specific flight information
     *
     * @param flightId flight identifier
     *
     * @return flight detail
     */
    FlightDetailDTO getFlight(int flightId);
}

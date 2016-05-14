package com.garbyou.flight.booking.service.query;

import com.garbyou.flight.booking.common.FindFlightQuery;
import com.garbyou.flight.booking.service.query.dto.FlightDTO;

import java.util.List;

/**
 * Contains method to perform flight query
 */
public interface FlightQueryService {

    List<FlightDTO> findFlight(FindFlightQuery query);
}

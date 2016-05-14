package com.garbyou.flight.booking.service.query.impl;

import com.garbyou.flight.booking.common.FindFlightQuery;
import com.garbyou.flight.booking.persistence.FlightDAO;
import com.garbyou.flight.booking.persistence.domain.Flight;
import com.garbyou.flight.booking.persistence.domain.Seat;
import com.garbyou.flight.booking.service.query.FlightQueryService;
import com.garbyou.flight.booking.service.query.dto.FlightDTO;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link com.garbyou.flight.booking.service.query.FlightQueryService}
 */
@Singleton
public class FlightQueryServiceImpl implements FlightQueryService{

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(FlightQueryServiceImpl.class);


    /**
     * Flight dao
     */
    @Inject
    private FlightDAO flightDao;

    @Override
    public List<FlightDTO> findFlight(final FindFlightQuery query) {
        logger.debug("Find flight with specific query");
        Preconditions.checkArgument(query != null, "Query cannot be null");

        List<Flight> flights = flightDao.findFlightByQuery(query);

        List<FlightDTO> dtos = new ArrayList<>();

        for (Flight flight : flights){
            FlightDTO dto = new FlightDTO();
            dto.setArrivalDate(flight.getArrivalDate());
            dto.setDepartureDate(flight.getDepartureDate());
            dto.setArrivalAirportCode(flight.getArrivalAirportCode());
            dto.setDepartureAirportCode(flight.getDepartureAirportCode());
            dto.setAirLine(flight.getAirLine());
            dto.setId(flight.getFlightId());

            int jSeatNumber = 0, ySeatNumber = 0, mSeatNumber = 0;
            for (Seat seat : flight.getSeats()){
                switch (seat.getCabinClass()){
                    case Y:
                        ySeatNumber++;
                        break;
                    case J:
                        jSeatNumber++;
                        break;
                    case M:
                        mSeatNumber++;
                        break;
                }
            }

            dto.getJSeatCabinInformation().setQuantity(jSeatNumber);
            dto.getYSeatCabinInformation().setQuantity(ySeatNumber);
            dto.getMSeatCabinInformation().setQuantity(mSeatNumber);
            dtos.add(dto);
        }

        return dtos;
    }
}

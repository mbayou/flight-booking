package com.garbyou.flight.booking.service.query.impl;

import com.garbyou.flight.booking.common.FindFlightQuery;
import com.garbyou.flight.booking.persistence.FlightDAO;
import com.garbyou.flight.booking.persistence.domain.Flight;
import com.garbyou.flight.booking.persistence.domain.Seat;
import com.garbyou.flight.booking.service.query.FlightQueryService;
import com.garbyou.flight.booking.service.query.dto.flight.FlightDTO;
import com.garbyou.flight.booking.service.query.dto.flight.FlightDetailDTO;
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
    public List<FlightDTO> findFlights(final FindFlightQuery query) {
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
            dto.setId(flight.getId());
            dto.setFlightId(flight.getFlightId());

            int jSeatNumber = 0, ySeatNumber = 0, mSeatNumber = 0;
            for (Seat seat : flight.getSeats()){
                if (seat.getBooking() == null) {
                    switch (seat.getCabinClass()) {
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
            }

            dto.getJSeatCabinInformation().setQuantity(jSeatNumber);
            dto.getYSeatCabinInformation().setQuantity(ySeatNumber);
            dto.getMSeatCabinInformation().setQuantity(mSeatNumber);
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public FlightDetailDTO getFlight(int flightId) {
        logger.debug("Get flight");

        Flight flight = flightDao.findById(flightId);
        if (flight == null){
            return null;
        }

        FlightDetailDTO dto = new FlightDetailDTO();

        dto.setArrivalDate(flight.getArrivalDate());
        dto.setDepartureDate(flight.getDepartureDate());
        dto.setArrivalAirportCode(flight.getArrivalAirportCode());
        dto.setDepartureAirportCode(flight.getDepartureAirportCode());
        dto.setAirLine(flight.getAirLine());
        dto.setId(flight.getId());
        dto.setFlightId(flight.getFlightId());

        int jSeatNumber = 0, ySeatNumber = 0, mSeatNumber = 0;
        float jPriceWhitoutTaxes = 0, yPriceWhitoutTaxes = 0, mPriceWhitoutTaxes = 0;
        float jTaxesPrice = 0, yTaxesPrice = 0, mTaxesPrice = 0;
        for (Seat seat : flight.getSeats()) {
            boolean isBookable = seat.getBooking() == null;
                switch (seat.getCabinClass()) {
                    case Y:
                        yPriceWhitoutTaxes = seat.getPriceWithoutTaxes();
                        yTaxesPrice = seat.getTaxesPrice();
                        if (isBookable) {
                            ySeatNumber++;
                        }
                        break;
                    case J:
                        jPriceWhitoutTaxes = seat.getPriceWithoutTaxes();
                        jTaxesPrice = seat.getTaxesPrice();
                        if (isBookable) {
                            jSeatNumber++;
                        }
                        break;
                    case M:
                        mPriceWhitoutTaxes = seat.getPriceWithoutTaxes();
                        mTaxesPrice = seat.getTaxesPrice();
                        if (isBookable) {
                            mSeatNumber++;
                        }
                        break;
                }
            }

        dto.getJSeatCabinInformation().setQuantity(jSeatNumber);
        dto.getJSeatCabinInformation().setPriceWithoutTaxes(jPriceWhitoutTaxes);
        dto.getJSeatCabinInformation().setTaxesPrice(jTaxesPrice);
        dto.getYSeatCabinInformation().setQuantity(ySeatNumber);
        dto.getYSeatCabinInformation().setPriceWithoutTaxes(yPriceWhitoutTaxes);
        dto.getYSeatCabinInformation().setTaxesPrice(yTaxesPrice);
        dto.getMSeatCabinInformation().setQuantity(mSeatNumber);
        dto.getMSeatCabinInformation().setPriceWithoutTaxes(mPriceWhitoutTaxes);
        dto.getMSeatCabinInformation().setTaxesPrice(mTaxesPrice);

        return dto;
    }
}

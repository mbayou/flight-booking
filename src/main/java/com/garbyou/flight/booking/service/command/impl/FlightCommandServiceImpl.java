package com.garbyou.flight.booking.service.command.impl;

import com.garbyou.flight.booking.Configuration;
import com.garbyou.flight.booking.common.CabinClass;
import com.garbyou.flight.booking.persistence.FlightDAO;
import com.garbyou.flight.booking.persistence.domain.Flight;
import com.garbyou.flight.booking.persistence.domain.Seat;
import com.garbyou.flight.booking.service.command.FlightCommandService;
import com.garbyou.flight.booking.service.handler.IATADBDatHandler;
import com.garbyou.flight.booking.service.handler.dto.flight.FlightDTO;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Locale;

/**
 * Implementation of {@link com.garbyou.flight.booking.service.command.FlightCommandService}
 */
@Singleton
public class FlightCommandServiceImpl implements FlightCommandService {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(FlightCommandServiceImpl.class);


    /**
     * Flight dao
     */
    @Inject
    private FlightDAO flightDao;

    @Override
    public void prepare() throws FileNotFoundException {
        logger.debug("Begin extract data from flight.dat");

        File iatadbFile = new File(Configuration.getResourcesPath() + "flight.dat");
        InputStream targetStream = new FileInputStream(iatadbFile);
        IATADBDatHandler handler = new IATADBDatHandler(targetStream);
        handler.extractData();

        for (FlightDTO dto : handler.getFlights()){
            Flight flight = new Flight();
            flight.setArrivalAirportCode(dto.getArrivalAirportCode());
            flight.setAirLine(dto.getAirLine());
            flight.setDepartureAirportCode(dto.getDepartureAirportCode());

            // Convert split date information to a datetime
            DateTime departure = DateTimeFormat.forPattern("yyyyddMMM").withLocale(Locale.ENGLISH).parseDateTime(DateTime.now().getYear() + dto.getDate());
            String departureTime;
            if (dto.getDepartureTime() >= 1000) {
                departureTime = String.valueOf(dto.getDepartureTime());
            } else if(dto.getDepartureTime() >= 100) {
                departureTime = "0" + dto.getDepartureTime();
            } else if(dto.getDepartureTime() >= 100) {
                departureTime = "00" + dto.getDepartureTime();
            } else {
                departureTime = "000" + dto.getDepartureTime();
            }

            departure = departure.hourOfDay().setCopy(departureTime.substring(0, 2));
            departure = departure.minuteOfHour().setCopy(departureTime.substring(2, 4));

            // Convert split date information to a datetime (use to get a timestamp, really useful for sql request)
            DateTime arrival = DateTimeFormat.forPattern("yyyyddMMM").withLocale(Locale.ENGLISH).parseDateTime(DateTime.now().getYear() + dto.getDate());
            String arrivalTime;
            if (dto.getArrivalTime() >= 1000) {
                arrivalTime = String.valueOf(dto.getArrivalTime());
            } else if(dto.getArrivalTime() >= 100) {
                arrivalTime = "0" + dto.getArrivalTime();
            } else if(dto.getArrivalTime() >= 100) {
                arrivalTime = "00" + dto.getArrivalTime();
            } else {
                arrivalTime = "000" + dto.getArrivalTime();
            }

            arrival = arrival.hourOfDay().setCopy(arrivalTime.substring(0, 2));
            arrival = arrival.minuteOfHour().setCopy(arrivalTime.substring(2, 4));

            flight.setDepartureDate(departure.getMillis());
            flight.setArrivalDate(arrival.getMillis());
            flight.setFlightId(dto.getFlightId());
            for (int i = 0; i < dto.getJSeatCabinInformation().getQuantity(); i++) {
                Seat seat = new Seat();
                seat.setCabinClass(CabinClass.J);
                seat.setPriceWithoutTaxes(dto.getJSeatCabinInformation().getPriceWithoutTaxes());
                seat.setTaxesPrice(dto.getJSeatCabinInformation().getTaxesPrice());
                seat.setFlight(flight);
                flight.getSeats().add(seat);
            }
            for (int i = 0; i < dto.getYSeatCabinInformation().getQuantity(); i++) {
                Seat seat = new Seat();
                seat.setCabinClass(CabinClass.Y);
                seat.setPriceWithoutTaxes(dto.getYSeatCabinInformation().getPriceWithoutTaxes());
                seat.setTaxesPrice(dto.getYSeatCabinInformation().getTaxesPrice());
                seat.setFlight(flight);
                flight.getSeats().add(seat);
            }
            for (int i = 0; i < dto.getMSeatCabinInformation().getQuantity(); i++) {
                Seat seat = new Seat();
                seat.setCabinClass(CabinClass.M);
                seat.setPriceWithoutTaxes(dto.getMSeatCabinInformation().getPriceWithoutTaxes());
                seat.setTaxesPrice(dto.getMSeatCabinInformation().getTaxesPrice());
                seat.setFlight(flight);
                flight.getSeats().add(seat);
            }

            this.flightDao.saveOrUpdate(flight);
        }
    }
}

package com.garbyou.flight.booking.service.handler;

import com.garbyou.flight.booking.service.handler.dto.FlightDTO;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Service use to extract data from iatadb
 */
public class IATADBDatHandler {
    /**
     * basic logger
     */
    private static final Logger logger = LoggerFactory.getLogger(IATADBDatHandler.class);

    /**
     * Index to retrieve air line
     */
    private static final int AIR_LINE = 0;

    /**
     * Index to retrieve flight identifier
     */
    private static final int FLIGHT_IDENTIFIER = 1;

    /**
     * Index to retrieve arrival code
     */
    private static final int ARRIVAL_CODE = 2;

    /**
     * Index to retrieve seat number cabin j
     */
    private static final int SEAT_NUMBER_CABIN_J = 3;

    /**
     * Index to retrieve seat number cabin y
     */
    private static final int SEAT_NUMBER_CABIN_Y = 4;

    /**
     * Index to retrieve seat number cabin m
     */
    private static final int SEAT_NUMBER_CABIN_M = 5;

    /**
     * Index to retrieve departure code
     */
    private static final int DEPARTURE_CODE = 6;

    /**
     * Index to retrieve departure time
     */
    private static final int DEPARTURE_TIME = 7;

    /**
     * Index to retrieve arrival time
     */
    private static final int ARRIVAL_TIME = 8;

    /**
     * Index to retrieve flight date
     */
    private static final int FLIGHT_DATE = 9;

    /**
     * Index to retrieve seat price without taxes cabin j
     */
    private static final int SEAT_PRICE_WITHOUT_TAXES_CABINE_J = 10;

    /**
     * Index to retrieve seat price without taxes cabin y
     */
    private static final int SEAT_PRICE_WITHOUT_TAXES_CABINE_Y = 11;

    /**
     * Index to retrieve seat price without taxes cabin m
     */
    private static final int SEAT_PRICE_WITHOUT_TAXES_CABINE_M = 12;

    /**
     * Index to retrieve seat taxes price cabin j
     */
    private static final int SEAT_TAXES_PRICE_CABINE_J = 13;

    /**
     * Index to retrieve seat taxes price cabin y
     */
    private static final int SEAT_TAXES_PRICE_CABINE_Y = 14;

    /**
     * Index to retrieve seat taxes price cabin m
     */
    private static final int SEAT_TAXES_PRICE_CABINE_M = 15;

    /**
     * internal csv file pointer
     */
    private final InputStream csvStream;

    /**
     * List to store the read values in csv.
     */
    private final List<FlightDTO> flights = new ArrayList<>();

    /**
     * Role string seperator
     */
    private static final char COL_SEPARATOR = ';';

    /**
     * Splitter used to split role String
     */
    private static final Splitter COL_SPLITTER = Splitter.on(COL_SEPARATOR).trimResults();

    /**
     * Default constructor needs a SharedStringsTable.
     *
     * @param csvStream the CSV to parse.
     */
    public IATADBDatHandler(final InputStream csvStream) {
        this.csvStream = csvStream;
    }

    /**
     * Read data from csv file and fill flight list
     */
    public void extractData() {
        logger.trace("extractData");

        BufferedReader br = null;
        try {
            int lineNumber = 1;
            br = new BufferedReader(new InputStreamReader(this.csvStream, Charsets.UTF_8));

            String line;
            while ((line = br.readLine()) != null) {
                logger.trace("Reading line #{}", lineNumber);

                final FlightDTO flight = extractFlightFromLine(line);
                if (flight != null) {
                    this.flights.add(flight);
                }
                lineNumber++;
            }
            br.close();
        } catch (IOException e) {
            logger.error("Error during data extraction", e);
        }
    }

    /**
     * Internal method to extract data from a single csv line represented as a string.
     *
     * @param line the line to be splitted
     * @return a flight instanciated from extracted line data or NULL if decoding error occurred
     */
    private FlightDTO extractFlightFromLine(final String line) {
        Iterable<String> iterable = COL_SPLITTER.split(line);
        // parsing iterable result is mandatory... to wired code to put a if switch in for loop !
        List<String> cells = new ArrayList<>();
        for (String cellVal : iterable) {
            cells.add(cellVal);
        }
        if (cells.isEmpty()) {
            return null;
        }
        logger.debug("Line: '{}'", line);
        try {

            FlightDTO flightDTO = new FlightDTO();
            flightDTO.setAirLine(cells.get(AIR_LINE));
            flightDTO.setFlightId(Integer.valueOf(cells.get(FLIGHT_IDENTIFIER)));
            flightDTO.setArrivalAirportCode(cells.get(ARRIVAL_CODE));
            flightDTO.setDepartureAirportCode(cells.get(DEPARTURE_CODE));
            flightDTO.setArrivalTime(Integer.valueOf(cells.get(ARRIVAL_TIME)));
            flightDTO.setDepartureTime(Integer.valueOf(cells.get(DEPARTURE_TIME)));
            flightDTO.setDate(cells.get(FLIGHT_DATE));
            flightDTO.getJSeatCabinInformation().setPriceWithoutTaxes(Float.valueOf(cells.get(SEAT_PRICE_WITHOUT_TAXES_CABINE_J)));
            flightDTO.getJSeatCabinInformation().setQuantity(Integer.valueOf(cells.get(SEAT_NUMBER_CABIN_J)));
            flightDTO.getJSeatCabinInformation().setTaxesPrice(Float.valueOf(cells.get(SEAT_TAXES_PRICE_CABINE_J)));
            flightDTO.getYSeatCabinInformation().setPriceWithoutTaxes(Float.valueOf(cells.get(SEAT_PRICE_WITHOUT_TAXES_CABINE_Y)));
            flightDTO.getYSeatCabinInformation().setQuantity(Integer.valueOf(cells.get(SEAT_NUMBER_CABIN_Y)));
            flightDTO.getYSeatCabinInformation().setTaxesPrice(Float.valueOf(cells.get(SEAT_TAXES_PRICE_CABINE_Y)));
            flightDTO.getMSeatCabinInformation().setPriceWithoutTaxes(Float.valueOf(cells.get(SEAT_PRICE_WITHOUT_TAXES_CABINE_M)));
            flightDTO.getMSeatCabinInformation().setQuantity(Integer.valueOf(cells.get(SEAT_NUMBER_CABIN_M)));
            flightDTO.getMSeatCabinInformation().setTaxesPrice(Float.valueOf(cells.get(SEAT_TAXES_PRICE_CABINE_M)));


            return flightDTO;
        } catch (RuntimeException e) {
            logger.error("------------- An error occurred during flight extraction --------------");
            logger.error("Exception occurred on line '{}' : '{}'", line, e.getMessage());
        }

        return null;
    }

    /**
     * Gets flights
     *
     * @return flights
     */
    public List<FlightDTO> getFlights() {
        return flights;
    }
}

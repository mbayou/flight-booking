package com.garbyou.flight.booking.common;

import com.google.common.base.Strings;

/**
 * Query to find flight
 */
public class FindFlightQuery {
    /**
     * Departure date
     */
    private Long departureDate;

    /**
     * Departure date
     */
    private Long arrivalDate;

    /**
     * Flight date
     */
    private Long flightDate;

    /**
     * Arrival airport code
     */
    private String arrivalAirportCode;

    /**
     * Departure airport code
     */
    private String departureAirportCode;


    public boolean isSet(){
        return !Strings.isNullOrEmpty(departureAirportCode)
                || !Strings.isNullOrEmpty(arrivalAirportCode)
                || flightDate != null
                || arrivalDate != null
                || departureDate != null;
    }

    /**
     * Sets new departureAirportCode
     *
     * @param departureAirportCode new value of departureAirportCode.
     */
    public void setDepartureAirportCode(final String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    /**
     * Gets departureAirportCode
     *
     * @return departureAirportCode
     */
    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    /**
     * Gets departureDate
     *
     * @return departureDate
     */
    public Long getDepartureDate() {
        return departureDate;
    }

    /**
     * Sets new arrivalAirportCode
     *
     * @param arrivalAirportCode new value of arrivalAirportCode.
     */
    public void setArrivalAirportCode(final String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    /**
     * Sets new departureDate
     *
     * @param departureDate new value of departureDate.
     */
    public void setDepartureDate(final Long departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Gets arrivalDate
     *
     * @return arrivalDate
     */
    public Long getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Gets arrivalAirportCode
     *
     * @return arrivalAirportCode
     */
    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    /**
     * Sets new arrivalDate
     *
     * @param arrivalDate new value of arrivalDate.
     */
    public void setArrivalDate(final Long arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * Gets flightDate
     *
     * @return flightDate
     */
    public Long getFlightDate() {
        return flightDate;
    }

    /**
     * Sets new flightDate
     *
     * @param flightDate new value of flightDate.
     */
    public void setFlightDate(final Long flightDate) {
        this.flightDate = flightDate;
    }
}

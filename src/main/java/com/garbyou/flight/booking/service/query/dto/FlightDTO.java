package com.garbyou.flight.booking.service.query.dto;

import com.garbyou.flight.booking.common.CabinClass;

/**
 * Data transfer obeject for flight
 */
public class FlightDTO {

    /**
     * Flight's identifier
     */
    private int id;

    /**
     * Flight's airline
     */
    private String airLine;

    /**
     * Flight's cabin j information seat
     */
    private SeatCabinInformation jSeatCabinInformation = new SeatCabinInformation(CabinClass.J);

    /**
     * Flight's cabin y information seat
     */
    private SeatCabinInformation ySeatCabinInformation = new SeatCabinInformation(CabinClass.Y);

    /**
     * Flight's cabin m information seat
     */
    private SeatCabinInformation mSeatCabinInformation = new SeatCabinInformation(CabinClass.M);

    /**
     * Arrival airport code
     */
    private String arrivalAirportCode;

    /**
     * Departure airport code
     */
    private String departureAirportCode;

    /**
     * Flight arrival date time
     */
    private long arrivalDate;

    /**
     * Flight departure date time
     */
    private long departureDate;

    /**
     * Represent seat cabin information
     */
    public static class SeatCabinInformation {

        /**
         * SeatCabinInformation cabin
         */
        private CabinClass cabinClass;

        /**
         * SeatCabinInformation quantity in sell
         */
        private int quantity;

        /**
         * Sets cabin class
         * @param cabinClass cabin class for seat
         */
        public SeatCabinInformation(final CabinClass cabinClass) {
            this.cabinClass = cabinClass;
        }

        /**
         * Gets quantity
         *
         * @return quantity
         */
        public int getQuantity() {
            return quantity;
        }

        /**
         * Sets new quantity
         *
         * @param quantity new value of quantity.
         */
        public void setQuantity(final int quantity) {
            this.quantity = quantity;
        }

        /**
         * Gets cabinClass
         *
         * @return cabinClass
         */
        public CabinClass getCabinClass() {
            return cabinClass;
        }
    }

    /**
     * Sets new id
     *
     * @param id new value of id.
     */
    public void setId(final int id) {
        this.id = id;
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
     * Sets new departureAirportCode
     *
     * @param departureAirportCode new value of departureAirportCode.
     */
    public void setDepartureAirportCode(final String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    /**
     * Gets departureDate
     *
     * @return departureDate
     */
    public long getDepartureDate() {
        return departureDate;
    }

    /**
     * Gets airLine
     *
     * @return airLine
     */
    public String getAirLine() {
        return airLine;
    }

    /**
     * Gets id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets ySeatCabinInformation
     *
     * @return ySeatCabinInformation
     */
    public SeatCabinInformation getYSeatCabinInformation() {
        return ySeatCabinInformation;
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
     * Sets new mSeatCabinInformation
     *
     * @param mSeatCabinInformation new value of mSeatCabinInformation.
     */
    public void setMSeatCabinInformation(final SeatCabinInformation mSeatCabinInformation) {
        this.mSeatCabinInformation = mSeatCabinInformation;
    }

    /**
     * Gets mSeatCabinInformation
     *
     * @return mSeatCabinInformation
     */
    public SeatCabinInformation getMSeatCabinInformation() {
        return mSeatCabinInformation;
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
     * Sets new airLine
     *
     * @param airLine new value of airLine.
     */
    public void setAirLine(final String airLine) {
        this.airLine = airLine;
    }

    /**
     * Sets new jSeatCabinInformation
     *
     * @param jSeatCabinInformation new value of jSeatCabinInformation.
     */
    public void setJSeatCabinInformation(final SeatCabinInformation jSeatCabinInformation) {
        this.jSeatCabinInformation = jSeatCabinInformation;
    }

    /**
     * Sets new arrivalDate
     *
     * @param arrivalDate new value of arrivalDate.
     */
    public void setArrivalDate(final long arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * Sets new departureDate
     *
     * @param departureDate new value of departureDate.
     */
    public void setDepartureDate(final long departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Sets new ySeatCabinInformation
     *
     * @param ySeatCabinInformation new value of ySeatCabinInformation.
     */
    public void setYSeatCabinInformation(final SeatCabinInformation ySeatCabinInformation) {
        this.ySeatCabinInformation = ySeatCabinInformation;
    }

    /**
     * Gets jSeatCabinInformation
     *
     * @return jSeatCabinInformation
     */
    public SeatCabinInformation getJSeatCabinInformation() {
        return jSeatCabinInformation;
    }

    /**
     * Gets arrivalDate
     *
     * @return arrivalDate
     */
    public long getArrivalDate() {
        return arrivalDate;
    }
}

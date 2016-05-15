package com.garbyou.flight.booking.service.handler.dto.flight;

import com.garbyou.flight.booking.common.CabinClass;

/**
 * Represent a flight
 */
public class FlightDTO {

    /**
     * Flight's air line
     */
    private String airLine;

    /**
     * Flight identifier
     */
    private int flightId;

    /**
     * Arrival airport code
     */
    private String arrivalAirportCode;

    /**
     * Departure airport code
     */
    private String departureAirportCode;

    /**
     * Flight departure time
     */
    private int departureTime;

    /**
     * Flight arrive time
     */
    private int arrivalTime;

    /**
     * Flight date
     */
    private String date;

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
         * SeatCabinInformation price (without taxes
         */
        private float priceWithoutTaxes;

        /**
         * SeatCabinInformation taxes price
         */
        private float taxesPrice;

        /**
         * Sets cabin class
         * @param cabinClass
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
         * Sets new taxesPrice
         *
         * @param taxesPrice new value of taxesPrice.
         */
        public void setTaxesPrice(final float taxesPrice) {
            this.taxesPrice = taxesPrice;
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

        /**
         * Sets new priceWithoutTaxes
         *
         * @param priceWithoutTaxes new value of priceWithoutTaxes.
         */
        public void setPriceWithoutTaxes(final float priceWithoutTaxes) {
            this.priceWithoutTaxes = priceWithoutTaxes;
        }

        /**
         * Sets new cabinClass
         *
         * @param cabinClass new value of cabinClass.
         */
        public void setCabinClass(final CabinClass cabinClass) {
            this.cabinClass = cabinClass;
        }

        /**
         * Gets priceWithoutTaxes
         *
         * @return priceWithoutTaxes
         */
        public float getPriceWithoutTaxes() {
            return priceWithoutTaxes;
        }

        /**
         * Gets taxesPrice
         *
         * @return taxesPrice
         */
        public float getTaxesPrice() {
            return taxesPrice;
        }

        /**
         * Return complete price (priceWithoutTaxes + taxesPrice)
         *
         * @return SeatCabinInformation's price
         */
        public float getPrice(){
            return priceWithoutTaxes + taxesPrice;
        }
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
     * Gets date
     *
     * @return date
     */
    public String getDate() {
        return date;
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
     * Sets new flightId
     *
     * @param flightId new value of flightId.
     */
    public void setFlightId(final int flightId) {
        this.flightId = flightId;
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
     * Sets new departureTime
     *
     * @param departureTime new value of departureTime.
     */
    public void setDepartureTime(final int departureTime) {
        this.departureTime = departureTime;
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
     * Gets arrivalTime
     *
     * @return arrivalTime
     */
    public int getArrivalTime() {
        return arrivalTime;
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
     * Sets new mSeatCabinInformation
     *
     * @param mSeatCabinInformation new value of mSeatCabinInformation.
     */
    public void setMSeatCabinInformation(final SeatCabinInformation mSeatCabinInformation) {
        this.mSeatCabinInformation = mSeatCabinInformation;
    }

    /**
     * Sets new date
     *
     * @param date new value of date.
     */
    public void setDate(final String date) {
        this.date = date;
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
     * Sets new arrivalTime
     *
     * @param arrivalTime new value of arrivalTime.
     */
    public void setArrivalTime(final int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Gets departureTime
     *
     * @return departureTime
     */
    public int getDepartureTime() {
        return departureTime;
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
     * Gets departureAirportCode
     *
     * @return departureAirportCode
     */
    public String getDepartureAirportCode() {
        return departureAirportCode;
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
     * Sets new departureAirportCode
     *
     * @param departureAirportCode new value of departureAirportCode.
     */
    public void setDepartureAirportCode(final String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    /**
     * Gets flightId
     *
     * @return flightId
     */
    public int getFlightId() {
        return flightId;
    }

    /**
     * Sets new arrivalAirportCode
     *
     * @param arrivalAirportCode new value of arrivalAirportCode.
     */
    public void setArrivalAirportCode(final String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }
}

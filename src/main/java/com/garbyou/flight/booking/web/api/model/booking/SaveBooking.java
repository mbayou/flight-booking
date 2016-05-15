package com.garbyou.flight.booking.web.api.model.booking;

import com.garbyou.flight.booking.common.CabinClass;

/**
 * Represent a booking to save
 */
public class SaveBooking {

    /**
     * Flight identifier
     */
    private int flightId;

    /**
     * Reservation cabin class
     */
    private CabinClass cabinClass;

    /**
     * Seat quantity booked
     */
    private int quantity;


    /**
     * Sets new flightId
     *
     * @param flightId new value of flightId.
     */
    public void setFlightId(final int flightId) {
        this.flightId = flightId;
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
     * Gets cabinClass
     *
     * @return cabinClass
     */
    public CabinClass getCabinClass() {
        return cabinClass;
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
     * Gets flightId
     *
     * @return flightId
     */
    public int getFlightId() {
        return flightId;
    }

    /**
     * Sets new quantity
     *
     * @param quantity new value of quantity.
     */
    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }
}

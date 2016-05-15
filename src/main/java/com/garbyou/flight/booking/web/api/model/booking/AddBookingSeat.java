package com.garbyou.flight.booking.web.api.model.booking;

/**
 * Represent seat to add
 */
public class AddBookingSeat {

    /**
     * Booking seat quantity to add (no cabin class, use existent)
     */
    private int quantity;


    /**
     * Sets new quantity
     *
     * @param quantity new value of quantity.
     */
    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets quantity
     *
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }
}

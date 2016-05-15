package com.garbyou.flight.booking.service.command.dto.booking;

/**
 * Represent seat to add
 */
public class AddBookingSeatDTO {

    /**
     * Booking identifier
     */
    private int id;

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

    /**
     * Sets new id
     *
     * @param id new value of id.
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets id
     *
     * @return id
     */
    public int getId() {
        return id;
    }
}

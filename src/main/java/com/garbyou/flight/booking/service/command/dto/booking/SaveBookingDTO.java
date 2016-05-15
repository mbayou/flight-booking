package com.garbyou.flight.booking.service.command.dto.booking;

import com.garbyou.flight.booking.common.CabinClass;

/**
 * Represent a booking to save
 */
public class SaveBookingDTO {

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
     * BookingDTO customer
     */
    private Customer customer;

    /**
     * Represent a customer
     */
    public static class Customer {

        /**
         * Customer First name
         */
        private String firstName;

        /**
         * Customer last Name
         */
        private String lastName;


        /**
         * Gets firstName
         *
         * @return firstName
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         * Sets new firstName
         *
         * @param firstName new value of firstName.
         */
        public void setFirstName(final String firstName) {
            this.firstName = firstName;
        }

        /**
         * Sets new lastName
         *
         * @param lastName new value of lastName.
         */
        public void setLastName(final String lastName) {
            this.lastName = lastName;
        }

        /**
         * Gets lastName
         *
         * @return lastName
         */
        public String getLastName() {
            return lastName;
        }
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

    /**
     * Gets customer
     *
     * @return customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets new customer
     *
     * @param customer new value of customer.
     */
    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }
}

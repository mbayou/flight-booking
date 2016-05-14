package com.garbyou.flight.booking.persistence.domain;

import com.garbyou.flight.booking.common.CabinClass;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Represent a flight
 */
@Entity
@Table(name = "seat")
public class Seat implements Serializable{

    /**
     * Serial UID
     */
    private static final long serialVersionUID = -3015335037370195574L;

    /**
     * Seat's identifier
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     * Seat's cabin class
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CabinClass cabinClass;

    /**
     * Seat price (without taxes)
     */
    @Column(nullable = false)
    private float priceWithoutTaxes;

    /**
     * Seat taxes price
     */
    @Column(nullable = false)
    private float taxesPrice;

    /**
     * Seat's flight
     */
    @ManyToOne(optional = false)
    private Flight flight;

    /**
     * Seat's booking
     */
    @ManyToOne(optional = true)
    private Booking booking;


    /**
     * Gets id
     *
     * @return id
     */
    public int getId() {
        return id;
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
     * Sets new id
     *
     * @param id new value of id.
     */
    public void setId(final int id) {
        this.id = id;
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
     * Sets new priceWithoutTaxes
     *
     * @param priceWithoutTaxes new value of priceWithoutTaxes.
     */
    public void setPriceWithoutTaxes(final float priceWithoutTaxes) {
        this.priceWithoutTaxes = priceWithoutTaxes;
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
     * Sets new taxesPrice
     *
     * @param taxesPrice new value of taxesPrice.
     */
    public void setTaxesPrice(final float taxesPrice) {
        this.taxesPrice = taxesPrice;
    }

    /**
     * Gets flight
     *
     * @return flight
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Sets new flight
     *
     * @param flight new value of flight.
     */
    public void setFlight(final Flight flight) {
        this.flight = flight;
    }

    /**
     * Sets new booking
     *
     * @param booking new value of booking.
     */
    public void setBooking(final Booking booking) {
        this.booking = booking;
    }

    /**
     * Gets booking
     *
     * @return booking
     */
    public Booking getBooking() {
        return booking;
    }
}

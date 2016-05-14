package com.garbyou.flight.booking.persistence.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent a flight
 */
@Entity
@Table(name = "booking")
public class Booking implements Serializable {

    /**
     * Seat's identifier
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     * Seats booked
     */
    @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY)
    @OrderBy("id ASC")
    private List<Seat> seats = new ArrayList<>();

    /**
     * Gets seats
     *
     * @return seats
     */
    public List<Seat> getSeats() {
        return seats;
    }

    /**
     * Sets new seats
     *
     * @param seats new value of seats.
     */
    public void setSeats(final List<Seat> seats) {
        this.seats = seats;
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

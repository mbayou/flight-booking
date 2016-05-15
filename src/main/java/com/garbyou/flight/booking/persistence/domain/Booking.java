package com.garbyou.flight.booking.persistence.domain;

import com.garbyou.flight.booking.common.BookingStatus;

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
     * Booking status
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    /**
     * Booking creation date
     */
    @Column(nullable = false)
    private long creationDate;

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

    /**
     * Sets new bookingStatus
     *
     * @param bookingStatus new value of bookingStatus.
     */
    public void setStatus(final BookingStatus bookingStatus) {
        this.status = bookingStatus;
    }

    /**
     * Gets bookingStatus
     *
     * @return bookingStatus
     */
    public BookingStatus getStatus() {
        return status;
    }
}

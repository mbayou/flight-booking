package com.garbyou.flight.booking.persistence.domain;

import com.garbyou.flight.booking.common.BookingStatus;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent a booking
 */
@Entity
@Table(name = "booking")
public class Booking implements Serializable {

    /**
     * BookingDTO's identifier
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
     * BookingDTO status
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    /**
     * BookingDTO creation date
     */
    @Column(nullable = false)
    private long creationDate;

    /**
     * BookingDTO customer information
     */
    @Embedded
    private Customer customer;

    public Booking() {
        creationDate = DateTime.now().getMillis();
    }

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

    /**
     * Sets new creationDate
     *
     * @param creationDate new value of creationDate.
     */
    public void setCreationDate(final long creationDate) {
        this.creationDate = creationDate;
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

    /**
     * Gets creationDate
     *
     * @return creationDate
     */
    public long getCreationDate() {
        return creationDate;
    }
}

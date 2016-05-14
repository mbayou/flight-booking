package com.garbyou.flight.booking.persistence.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent a flight
 */
@Entity
@Table(name = "flight")
public class Flight implements Serializable{

    /**
     * Serial UID
     */
    private static final long serialVersionUID = 3219575657862229142L;

    /**
     * Flight's identifier (use for BDD because the ID in flight.dat is not unique, that's it's completely crazy but it's true !)
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     * Flight identifier
     */
    @Column(nullable = false)
    private int flightId;

    /**
     * Flight's air line
     */
    @Column(nullable = false)
    private String airLine;

    /**
     * Arrival airport code
     */
    @Column(nullable = false)
    private String arrivalAirportCode;

    /**
     * Departure airport code
     */
    @Column(nullable = false)
    private String departureAirportCode;

    /**
     * Flight arrival date time
     */
    @Column(nullable = false)
    private long arrivalDate;

    /**
     * Flight departure date time
     */
    @Column(nullable = false)
    private long departureDate;

    /**
     * Flight's seats
     */
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("id ASC")
    private List<Seat> seats = new ArrayList<>();

    /**
     * Gets airLine
     *
     * @return airLine
     */
    public String getAirLine() {
        return airLine;
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
     * Sets new airLine
     *
     * @param airLine new value of airLine.
     */
    public void setAirLine(final String airLine) {
        this.airLine = airLine;
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
     * Gets id
     *
     * @return id
     */
    public int getFlightId() {
        return flightId;
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
     * Sets new id
     *
     * @param id new value of id.
     */
    public void setFlightId(final int id) {
        this.flightId = id;
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
     * Sets new seats
     *
     * @param seats new value of seats.
     */
    public void setSeats(final List<Seat> seats) {
        this.seats = seats;
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
     * Sets new arrivalDate
     *
     * @param arrivalDate new value of arrivalDate.
     */
    public void setArrivalDate(final long arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * Gets arrivalDate
     *
     * @return arrivalDate
     */
    public long getArrivalDate() {
        return arrivalDate;
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
     * Sets new departureDate
     *
     * @param departureDate new value of departureDate.
     */
    public void setDepartureDate(final long departureDate) {
        this.departureDate = departureDate;
    }
}

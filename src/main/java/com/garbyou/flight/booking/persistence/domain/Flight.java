package com.garbyou.flight.booking.persistence.domain;

import javax.persistence.*;
import java.io.Serializable;

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
     * Flight's identifier
     */
    @Id
    private int id;

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
     * Flight departure time
     */
    @Column(nullable = false)
    private int departureTime;

    /**
     * Flight arrive time
     */
    @Column(nullable = false)
    private int arrivalTime;

    /**
     * Flight date
     */
    @Column(nullable = false)
    private String date;

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
     * Gets date
     *
     * @return date
     */
    public String getDate() {
        return date;
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
     * Sets new departureTime
     *
     * @param departureTime new value of departureTime.
     */
    public void setDepartureTime(final int departureTime) {
        this.departureTime = departureTime;
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
     * Sets new arrivalAirportCode
     *
     * @param arrivalAirportCode new value of arrivalAirportCode.
     */
    public void setArrivalAirportCode(final String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
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
     * Gets id
     *
     * @return id
     */
    public int getId() {
        return id;
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
     * Sets new date
     *
     * @param date new value of date.
     */
    public void setDate(final String date) {
        this.date = date;
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
     * Gets arrivalAirportCode
     *
     * @return arrivalAirportCode
     */
    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }
}

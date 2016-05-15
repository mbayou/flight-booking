package com.garbyou.flight.booking.persistence.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Represents a customer
 */
@Embeddable
public class Customer implements Serializable{

    /**
     * Serial UID
     */
    private static final long serialVersionUID = -6681804739542651047L;

    /**
     * Customer first name
     */
    private String firstName;

    /**
     * Customer first name
     */
    private String lastName;


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

    /**
     * Gets firstName
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }
}

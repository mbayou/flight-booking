package com.garbyou.flight.booking.web.api.model;

/**
 * Represents an identifier. Used when services returns identifier
 */
public class LongIdentifier {

    /**
     * Identifier
     */
    private long id;

    /**
     * Default constructor
     *
     * @param id identifier
     */
    public LongIdentifier(final long id) {
        this.id = id;
    }

    /**
     * Gets Identifier.
     *
     * @return Value of Identifier.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets new Identifier.
     *
     * @param id New value of Identifier.
     */
    public void setId(final long id) {
        this.id = id;
    }
}

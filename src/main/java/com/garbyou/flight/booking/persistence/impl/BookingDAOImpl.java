package com.garbyou.flight.booking.persistence.impl;

import com.garbyou.flight.booking.common.FindFlightQuery;
import com.garbyou.flight.booking.persistence.BookingDAO;
import com.garbyou.flight.booking.persistence.FlightDAO;
import com.garbyou.flight.booking.persistence.domain.Booking;
import com.garbyou.flight.booking.persistence.domain.Flight;
import com.garbyou.flight.booking.persistence.domain.Flight_;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Implementation of {@link com.garbyou.flight.booking.persistence.BookingDAO}
 */
public class BookingDAOImpl implements BookingDAO {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(BookingDAOImpl.class);

    /**
     * Entity Manager used to perform database operations
     */
    @Inject
    private Provider<EntityManager> em;

    @Override
    @Transactional
    public void saveOrUpdate(final Booking booking) {
        this.em.get().persist(booking);
        logger.trace("Booking with id '{}' saved", booking.getId());
    }

    @Override
    public Booking findById(final int id) {
        logger.trace("Searching booking with id '{}'", id);
        Booking ret = this.em.get().find(Booking.class, id);
        logger.trace("booking type with id '{}' {}", id, ret == null ? "unknown" : "found");

        return ret;
    }
}

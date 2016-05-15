package com.garbyou.flight.booking.persistence.impl;

import com.garbyou.flight.booking.persistence.SeatDAO;
import com.garbyou.flight.booking.persistence.domain.Seat;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Provider;
import javax.persistence.EntityManager;

/**
 * Implementation of {@link SeatDAO}
 */
public class SeatDAOImpl implements SeatDAO {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(SeatDAOImpl.class);

    /**
     * Entity Manager used to perform database operations
     */
    @Inject
    private Provider<EntityManager> em;

    @Override
    @Transactional
    public void saveOrUpdate(final Seat seat) {
        this.em.get().persist(seat);
        logger.trace("Seat with id '{}' saved", seat.getId());
    }

}

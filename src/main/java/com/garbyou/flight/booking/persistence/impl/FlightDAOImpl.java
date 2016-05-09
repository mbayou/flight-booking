package com.garbyou.flight.booking.persistence.impl;

import com.garbyou.flight.booking.persistence.FlightDAO;
import com.garbyou.flight.booking.persistence.domain.Flight;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Provider;
import javax.persistence.EntityManager;

/**
 * Implementation of {@link com.garbyou.flight.booking.persistence.FlightDAO}
 */
public class FlightDAOImpl implements FlightDAO{

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(FlightDAOImpl.class);

    /**
     * Entity Manager used to perform database operations
     */
    @Inject
    private Provider<EntityManager> em;

    @Override
    @Transactional
    public void saveOrUpdate(Flight flight) {
        this.em.get().persist(flight);
        logger.trace("Flight with id '{}' saved", flight.getId());
    }
}

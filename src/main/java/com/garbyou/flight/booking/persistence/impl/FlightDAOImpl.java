package com.garbyou.flight.booking.persistence.impl;

import com.garbyou.flight.booking.common.FindFlightQuery;
import com.garbyou.flight.booking.persistence.FlightDAO;
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
 * Implementation of {@link com.garbyou.flight.booking.persistence.FlightDAO}
 */
public class FlightDAOImpl implements FlightDAO {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(FlightDAOImpl.class);

    /**
     * One hours in millisecond
     */
    private static final long HOURS_MILLIS = 3600000;

    /**
     * Entity Manager used to perform database operations
     */
    @Inject
    private Provider<EntityManager> em;

    @Override
    @Transactional
    public void saveOrUpdate(final Flight flight) {
        this.em.get().persist(flight);
        logger.trace("Flight with id '{}' saved", flight.getFlightId());
    }

    @Override
    public Flight findById(final int id) {
        logger.trace("Searching flight with id '{}'", id);
        Flight ret = this.em.get().find(Flight.class, id);
        logger.trace("flight type with id '{}' {}", id, ret == null ? "unknown" : "found");

        return ret;
    }

    @Override
    public List<Flight> findFlightByQuery(final FindFlightQuery query) {
        StringBuilder queryBuilder = new StringBuilder("select f from ");
        queryBuilder.append(Flight.class.getName()).append(" as f ");
        StringBuilder whereClause = new StringBuilder();
        if (query.isSet()) {
            if (!Strings.isNullOrEmpty(query.getArrivalAirportCode())) {
                if (whereClause.toString().equals("")) {
                    whereClause.append("where ");
                }
                whereClause.append("f.").append(Flight_.arrivalAirportCode.getName()).append(" = :arrivalairportcode ");
            }
            if (!Strings.isNullOrEmpty(query.getDepartureAirportCode())) {
                if (whereClause.toString().equals("")) {
                    whereClause.append("where ");
                } else {
                    whereClause.append(" and ");
                }
                whereClause.append("f.").append(Flight_.departureAirportCode.getName()).append(" = :departureairportcode ");
            }
            if (query.getArrivalDate() != null) {
                if (whereClause.toString().equals("")) {
                    whereClause.append("where ");
                } else {
                    whereClause.append(" and ");
                }
                whereClause.append("f.").append(Flight_.arrivalDate.getName()).append(" >= :arrivaldatebegin ");
                whereClause.append(" and f.").append(Flight_.arrivalDate.getName()).append(" <= :arrivaldateend ");
            }
            if (query.getDepartureDate() != null) {
                if (whereClause.toString().equals("")) {
                    whereClause.append("where ");
                } else {
                    whereClause.append(" and ");
                }
                whereClause.append("f.").append(Flight_.departureDate.getName()).append(" >= :departuredatebegin ");
                whereClause.append(" and f.").append(Flight_.departureDate.getName()).append(" <= :departuredateend ");
            }
            if (query.getFlightDate() != null) {
                if (whereClause.toString().equals("")) {
                    whereClause.append("where ");
                } else {
                    whereClause.append(" and ");
                }
                whereClause.append("f.").append(Flight_.departureDate.getName()).append(" >= :flightdatebegin ");
                whereClause.append(" and f.").append(Flight_.departureDate.getName()).append(" <= :flightdateend ");
            }
        }
        queryBuilder.append(whereClause);
        queryBuilder.append("order by f.").append(Flight_.departureDate.getName()).append(" ASC ");
        Query queryHql = em.get().createQuery(queryBuilder.toString());
        if (query.isSet()) {
            if (!Strings.isNullOrEmpty(query.getArrivalAirportCode())) {
                queryHql.setParameter("arrivalairportcode", query.getArrivalAirportCode());
            }
            if (!Strings.isNullOrEmpty(query.getDepartureAirportCode())) {
                queryHql.setParameter("departureairportcode", query.getDepartureAirportCode());
            }
            if (query.getArrivalDate() != null) {
                queryHql.setParameter("arrivaldatebegin", query.getArrivalDate() - HOURS_MILLIS);
                queryHql.setParameter("arrivaldateend", query.getArrivalDate());
            }
            if (query.getDepartureDate() != null) {
                queryHql.setParameter("departuredatebegin", query.getDepartureDate() - HOURS_MILLIS);
                queryHql.setParameter("departuredateend", query.getDepartureDate());
            }
            if (query.getFlightDate() != null) {
                DateTime flightDateBegin = new DateTime(query.getFlightDate());
                flightDateBegin.hourOfDay().setCopy(0);
                DateTime flightDateEnd = new DateTime(query.getFlightDate());
                flightDateEnd = flightDateEnd.plusDays(1);
                queryHql.setParameter("flightdatebegin", flightDateBegin.getMillis());
                queryHql.setParameter("flightdateend", flightDateEnd.getMillis());
            }
        }

        List<Flight> flights = queryHql.getResultList();

        logger.debug("{} Flights is retrieved", flights.size());

        return flights;
    }
}

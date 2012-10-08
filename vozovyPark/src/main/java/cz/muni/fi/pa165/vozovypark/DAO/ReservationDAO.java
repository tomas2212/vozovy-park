/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.Reservation;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Tomas
 */
public class ReservationDAO {
    //@PersistenceContext
    EntityManager entityManager;

    public ReservationDAO(EntityManager em) {
        this.entityManager = em;
    }
    
 
    public Reservation getReservationById(Long id){
        Query q = entityManager.createNamedQuery(Reservation.FIND_BY_ID);
        q.setParameter("id", id);
        return (Reservation) q.getSingleResult();
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import cz.muni.fi.pa165.vozovypark.entities.Reservation;
import java.util.List;
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
    
    public void insert(Reservation r) {
        entityManager.persist(r);
    }
    
    public void update(Reservation r) {
        entityManager.merge(r);
    }
    
    public void remove(Reservation r) {
        entityManager.remove(entityManager.merge(r));
    }

  public List<Reservation> getReservationByCar(Car car) {
       Query q = entityManager.createNamedQuery(Reservation.FIND_BY_CAR);
       q.setParameter("car", car);
      return q.getResultList();
   }
   
   public List<Reservation> getReservationByEmployee(Employee employee) {
       Query q = entityManager.createNamedQuery(Reservation.FIND_BY_EMPLOYEE);
       q.setParameter("employee", employee);
       return q.getResultList();
   }
   
   public List<Reservation> getReservationByCarAndEmployee(Car car, Employee employee) {
       Query q = entityManager.createNamedQuery(Reservation.FIND_BY_CAR_AND_EMPLOYEE);
       q.setParameter("car", car);
       q.setParameter("employee", employee);
       return q.getResultList();
   }
}
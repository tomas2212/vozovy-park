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
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Eduard Krak
 */

public class ReservationDAOImpl implements ReservationDAO {
    //@PersistenceContext
    EntityManagerFactory entityManagerFactory;

    public ReservationDAOImpl(EntityManagerFactory emf) {
        this.entityManagerFactory = emf;
    }
   
    public Reservation getReservationById(Long id){
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        Query q = entityManager.createNamedQuery(Reservation.FIND_BY_ID);
        q.setParameter("id", id);
        return (Reservation) q.getSingleResult();
    }
    
    public void insert(Reservation r) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.persist(r);
    }
    
    public void update(Reservation r) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.merge(r);
    }
    
    public void remove(Reservation r) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.remove(entityManager.merge(r));
    }

  public List<Reservation> getReservationByCar(Car car) {
      EntityManager entityManager = this.entityManagerFactory.createEntityManager();
       Query q = entityManager.createNamedQuery(Reservation.FIND_BY_CAR);
       q.setParameter("car", car);
      return q.getResultList();
   }
   
   public List<Reservation> getReservationByEmployee(Employee employee) {
       EntityManager entityManager = this.entityManagerFactory.createEntityManager();
       Query q = entityManager.createNamedQuery(Reservation.FIND_BY_EMPLOYEE);
       q.setParameter("employee", employee);
       return q.getResultList();
   }
   
   public List<Reservation> getReservationByCarAndEmployee(Car car, Employee employee) {
       EntityManager entityManager = this.entityManagerFactory.createEntityManager();
       Query q = entityManager.createNamedQuery(Reservation.FIND_BY_CAR_AND_EMPLOYEE);
       q.setParameter("car", car);
       q.setParameter("employee", employee);
       return q.getResultList();
   }
   
   public List<Reservation> getAllReservations() {
       EntityManager entityManager = this.entityManagerFactory.createEntityManager();
       Query q = entityManager.createQuery(Reservation.FIND_ALL);
       return q.getResultList();
   }
}
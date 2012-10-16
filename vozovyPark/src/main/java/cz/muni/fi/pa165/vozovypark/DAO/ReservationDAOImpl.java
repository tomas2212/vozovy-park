package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import cz.muni.fi.pa165.vozovypark.entities.Reservation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Eduard Krak
 */

public class ReservationDAOImpl implements ReservationDAO {

    EntityManagerFactory entityManagerFactory;

    public ReservationDAOImpl(EntityManagerFactory emf) {
        this.entityManagerFactory = emf;
    }
   
    public Reservation getReservationById(Long id){
        if(id==null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();    
        return entityManager.find(Reservation.class, id);
    }
    
    public void insert(Reservation r) {
        if(r == null) {
            throw new IllegalArgumentException("You must specify reservation to insert");
        }
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(r);
        entityManager.getTransaction().commit();
    }
    
    public void update(Reservation r) {
        if(r==null){
            throw new IllegalArgumentException("You must specify reservation to update");
        }
        if(r.getId()==null) {
            throw new IllegalArgumentException("Reservation to update must have specified ID");
        }
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(r);
        entityManager.getTransaction().commit();
    }
    
    public void remove(Reservation r) {
        if(r==null){
            throw new IllegalArgumentException("You must specify reservation to remove");
        }
        if(r.getId()==null) {
            throw new IllegalArgumentException("Reservation to remove must have specified ID");
        }
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(r));
        entityManager.getTransaction().commit();
    }

  public List<Reservation> getReservationByCar(Car car) {
      if(car==null){
            throw new IllegalArgumentException("You must specify reservation car");
        }
      if(car.getId()==null) {
            throw new IllegalArgumentException("Car must have specified ID");
        }
      EntityManager entityManager = this.entityManagerFactory.createEntityManager();
       Query q = entityManager.createNamedQuery(Reservation.FIND_BY_CAR);
       q.setParameter("car", car);
      return q.getResultList();
   }
   
   public List<Reservation> getReservationByEmployee(Employee employee) {
       if(employee==null){
            throw new IllegalArgumentException("You must specify reservation employee");
        }
       if(employee.getId()==null) {
            throw new IllegalArgumentException("Employee must have specified ID");
        }
       EntityManager entityManager = this.entityManagerFactory.createEntityManager();
       Query q = entityManager.createNamedQuery(Reservation.FIND_BY_EMPLOYEE);
       q.setParameter("employee", employee);
       return q.getResultList();
   }
   
   public List<Reservation> getReservationByCarAndEmployee(Car car, Employee employee) {
       if(car==null){
            throw new IllegalArgumentException("You must specify reservation car");
        }
      if(car.getId()==null) {
            throw new IllegalArgumentException("Car must have specified ID");
        }
      if(employee==null){
            throw new IllegalArgumentException("You must specify reservation employee");
        }
       if(employee.getId()==null) {
            throw new IllegalArgumentException("Employee must have specified ID");
        }
       EntityManager entityManager = this.entityManagerFactory.createEntityManager();
       Query q = entityManager.createNamedQuery(Reservation.FIND_BY_CAR_AND_EMPLOYEE);
       q.setParameter("car", car);
       q.setParameter("employee", employee);
       return q.getResultList();
   }
   
   public List<Reservation> getAllReservations() {
       EntityManager entityManager = this.entityManagerFactory.createEntityManager();
       TypedQuery<Reservation> q = entityManager.createNamedQuery(Reservation.FIND_ALL, Reservation.class);
       return q.getResultList();
   }
}
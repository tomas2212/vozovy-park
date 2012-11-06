package cz.muni.fi.pa165.ejb.dao;


import cz.muni.fi.pa165.ejb.entities.Car;
import cz.muni.fi.pa165.ejb.entities.Employee;
import cz.muni.fi.pa165.ejb.entities.Reservation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author Eduard Krak
 */
@Stateless
public class ReservationDAOImpl implements ReservationDAO {

   @PersistenceContext
    protected EntityManager entityManager;

    public ReservationDAOImpl() {
        
    }
   
    public Reservation getReservationById(Long id){
        if(id==null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
            
        return entityManager.find(Reservation.class, id);
    }
    
    public void insert(Reservation r) {
        if(r == null) {
            throw new IllegalArgumentException("You must specify reservation to insert");
        }        
        entityManager.persist(r);
        
    }
    
    public void update(Reservation r) {
        if(r==null){
            throw new IllegalArgumentException("You must specify reservation to update");
        }
        if(r.getId()==null) {
            throw new IllegalArgumentException("Reservation to update must have specified ID");
        }
        entityManager.merge(r);
        
    }
    
    public void remove(Reservation r) {
        if(r==null){
            throw new IllegalArgumentException("You must specify reservation to remove");
        }
        if(r.getId()==null) {
            throw new IllegalArgumentException("Reservation to remove must have specified ID");
        }
        
        entityManager.remove(entityManager.merge(r));
       
    }

  public List<Reservation> getReservationByCar(Car car) {
      if(car==null){
            throw new IllegalArgumentException("You must specify reservation car");
        }
      if(car.getId()==null) {
            throw new IllegalArgumentException("Car must have specified ID");
        }
      
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
       
       Query q = entityManager.createNamedQuery(Reservation.FIND_BY_CAR_AND_EMPLOYEE);
       q.setParameter("car", car);
       q.setParameter("employee", employee);
       return q.getResultList();
   }
   
   public List<Reservation> getAllReservations() {
       
       TypedQuery<Reservation> q = entityManager.createNamedQuery(Reservation.FIND_ALL, Reservation.class);
       return q.getResultList();
   }
   
   public List<Reservation> getReservationsToConfirm() {
       TypedQuery<Reservation> q = entityManager.createNamedQuery(Reservation.FIND_TO_CONFIRM, Reservation.class);
       return q.getResultList();
   }
}
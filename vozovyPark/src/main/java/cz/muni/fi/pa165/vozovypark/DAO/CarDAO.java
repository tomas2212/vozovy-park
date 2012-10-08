/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.Car;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Tomas Svrcek
 */
public class CarDAO {
    //@PersistenceContext

    EntityManager entityManager;

    public CarDAO(EntityManager em) {
        this.entityManager = em;
    }

    public Car getCarById(Long id) {
        Query q = entityManager.createNamedQuery(Car.FIND_BY_ID);
        q.setParameter("id", id);
        return (Car) q.getSingleResult();
    }

    public Car getCarBySpz(String spz) {
        Query q = entityManager.createNamedQuery(Car.FIND_BY_SPZ);
        q.setParameter("spz", spz);
        return (Car) q.getSingleResult();
    }
    
    public List<Car> getAllCars() {
        TypedQuery<Car> q = (TypedQuery<Car>) entityManager.createNamedQuery("SELECT e FROM Car e");
        return q.getResultList();
    }
 
    public void insert(Car car) {
        entityManager.persist(car);
    }

    public void remove(Car car) {
        entityManager.remove(entityManager.merge(car));
    }

    public void update(Car car) {
        entityManager.merge(car);
    }
}

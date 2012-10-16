/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Tomas Svrcek
 */
public class CarDAOImpl implements CarDAO{
    //@PersistenceContext

    EntityManagerFactory entityManagerFactory;

    public CarDAOImpl(EntityManagerFactory emf) {

        this.entityManagerFactory = emf;
    }

    public Car getCarById(Long id) {
        EntityManager em = this.entityManagerFactory.createEntityManager();
        return em.find(Car.class, id);
    }

    public Car getCarBySpz(String spz) {
        if(spz == null){
            throw new IllegalArgumentException("You have to set spz.");
        }
        EntityManager em = this.entityManagerFactory.createEntityManager();
        Query q = em.createNamedQuery(Car.FIND_BY_SPZ);
        q.setParameter("spz", spz);
        return (Car) q.getSingleResult();
    }
    
    public List<Car> getAllCars() {
        EntityManager em = entityManagerFactory.createEntityManager();
        TypedQuery<Car> q = em.createQuery("SELECT e FROM Car e", Car.class);
        return q.getResultList();   
    }
 
    public void insert(Car car) {
        if(car == null){
            throw new IllegalArgumentException("You have to set car");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(car);
        em.getTransaction().commit();
    }

    public void remove(Car car) {
        if(car == null){
            throw new IllegalArgumentException("You have to set car");
        }
        if(car.getId() == null){
            throw new IllegalArgumentException("Can't remove, because entity doesn't have ID");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(car));
        em.getTransaction().commit();
    }

    public void update(Car car) {
        if(car == null){
            throw new IllegalArgumentException("You have to specify car");
        }
        if(car.getId() == null){
            throw new IllegalArgumentException("Can't update, because entity doesn't have ID");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(car);
        em.getTransaction().commit();
    }

    public List<Car> getAllCarsWithHigherLevel(CompanyLevel companyLevel) {
        if (companyLevel == null) {
            throw new IllegalArgumentException("You have to specify company level");
        }
        if (companyLevel.getId() == null) {
            throw new IllegalArgumentException("Company level must have specified ID");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        TypedQuery<Car> q = em.createQuery("SELECT e FROM Car e INNER JOIN FETCH e.companyLevel as c WHERE (c.levelValue >= :companyLevelValue)", Car.class);
        q.setParameter("companyLevelValue", companyLevel.getLevelValue());
        return q.getResultList();   
    }
}

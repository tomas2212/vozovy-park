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
        return em.find(Car.class,id);
    }

    public Car getCarBySpz(String spz) {
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
        EntityManager em = entityManagerFactory.createEntityManager();
        em.persist(car);
    }

    public void remove(Car car) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.remove(em.merge(car));
    }

    public void update(Car car) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.merge(car);
    }

    public List<Car> getAllCarsWithHigherLevel(CompanyLevel companyLevel) {
        EntityManager em = entityManagerFactory.createEntityManager();
        TypedQuery<Car> q = em.createQuery("SELECT e FROM Car e WHERE (e.levelValue=>:companyLevel)", Car.class);
        return q.getResultList();   
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.Car;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Tomas
 */
public class CarDAO {
    //@PersistenceContext
    EntityManager entityManager;

    public CarDAO(EntityManager em) {
        this.entityManager = em;
    }
    
    
   
    public Car getCarById(Long id){
        Query q = entityManager.createNamedQuery(Car.FIND_BY_ID);
        q.setParameter("id", id);
        return (Car) q.getSingleResult();
    }
}

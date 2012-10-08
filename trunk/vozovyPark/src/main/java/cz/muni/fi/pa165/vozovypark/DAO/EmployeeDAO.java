/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.Employee;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tomas
 */

public class EmployeeDAO {
    //@PersistenceContext
    EntityManager entityManager;

    public EmployeeDAO(EntityManager em) {
        this.entityManager = em;
    }
    
    
   
    public Employee getEmployeeById(Long id){
        Query q = entityManager.createNamedQuery(Employee.FIND_BY_ID);
        q.setParameter("id", id);
        return (Employee) q.getSingleResult();
    }
}

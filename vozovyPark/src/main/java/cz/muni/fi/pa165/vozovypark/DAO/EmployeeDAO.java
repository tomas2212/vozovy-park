/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.Employee;
import javax.persistence.EntityManager;

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
        return this.entityManager.find(Employee.class, id);
        
    }
}

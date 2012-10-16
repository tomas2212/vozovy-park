/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Lukas Maticky
 */
public class EmployeeDAOImpl implements EmployeeDAO {

//    EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;

    public EmployeeDAOImpl(EntityManagerFactory factory) {
        this.entityManagerFactory = factory;
    }

    public Employee getEmployeeById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.find(Employee.class, id);
        //  return this.entityManager.find(Employee.class, id);
    }

    public Employee getEmployeeByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("you must specify the name of employee");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createNamedQuery(Employee.FIND_BY_NAME);
        q.setParameter("name", name);
        return (Employee) q.getSingleResult();
    }

    public Employee getEmployeeByAddress(String address) {
        if (address == null) {
            throw new IllegalArgumentException("you must specify the address of employee");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createNamedQuery(Employee.FIND_BY_ADDRESS);
        q.setParameter("address", address);
        return (Employee) q.getSingleResult();
    }

    public void insert(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("you must specify company level");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
    }

    public void remove(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("you must specify employee");
        }
        if (employee.getId() == null) {
            throw new IllegalArgumentException("cant update not persit entity");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(employee));
        em.getTransaction().commit();
    }

    public void update(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("you must specify employee");
        }
        if (employee.getId() == null) {
            throw new IllegalArgumentException("cant update not persit entity");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(employee);
        em.getTransaction().commit();
    }

    public List<Employee> getAllEmployee() {
        EntityManager em = entityManagerFactory.createEntityManager();
        TypedQuery<Employee> q = (TypedQuery<Employee>) em.createQuery("SELECT e FROM Employee e");
        return q.getResultList();
    }

    public List<Employee> getAllEmployeeWithHigherLevel(CompanyLevel companyLevel) {
        EntityManager em = entityManagerFactory.createEntityManager();
        TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e INNER JOIN FETCH e.companyLevel as c WHERE (c.levelValue >= :companyLevelValue)", Employee.class);
        q.setParameter("companyLevelValue", companyLevel.getLevelValue());
        return q.getResultList();   
    }
}

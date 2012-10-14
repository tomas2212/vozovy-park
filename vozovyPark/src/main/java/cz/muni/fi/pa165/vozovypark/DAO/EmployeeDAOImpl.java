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
 * @author Lukas
 */
public class EmployeeDAOImpl implements EmployeeDAO {

//    EntityManager entityManager;
    EntityManagerFactory entityManagerFactory;

    public EmployeeDAOImpl(EntityManagerFactory factory) {
        this.entityManagerFactory = factory;
    }

    public Employee getEmployeeById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.find(Employee.class, id);
        //  return this.entityManager.find(Employee.class, id);
    }

    public Employee getEmployeeByName(String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createNamedQuery(Employee.FIND_BY_NAME);
        q.setParameter("name", name);
        return (Employee) q.getSingleResult();
    }

    public Employee getEmployeeByAddress(String address) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createNamedQuery(Employee.FIND_BY_ADDRESS);
        q.setParameter("address", address);
        return (Employee) q.getSingleResult();
    }

    public void insert(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
    }

    public void remove(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(employee));
        em.getTransaction().commit();
    }

    public void update(Employee employee) {
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
        TypedQuery<Employee> q = (TypedQuery<Employee>) em.createQuery("SELECT e FROM Employee e WHERE (e.levelValue=>:companyLevel)");
        return q.getResultList();
    }
}

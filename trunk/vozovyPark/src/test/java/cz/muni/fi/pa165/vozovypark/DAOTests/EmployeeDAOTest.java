/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.DAOTests;


import cz.muni.fi.pa165.vozovypark.DAO.EmployeeDAO;
import cz.muni.fi.pa165.vozovypark.DAO.EmployeeDAOImpl;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Andrej Bauer
 */
public class EmployeeDAOTest {
    
    private EntityManagerFactory emf;
    
    public EmployeeDAOTest() {
    }
    Connection connection;
    @Before
    public void setUp() {
        
        try {
            
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:unit-testing-jpa", "sa", "");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Exception during HSQL database startup.");
        }
        try {
            
            emf = Persistence.createEntityManagerFactory("testPU");
            
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Exception during JPA EntityManager instanciation.");
        }
    }
    
    @After
    public void tearDown() {
        if (emf != null) {
            emf.close();
        }
        
        try {
            connection.createStatement().execute("SHUTDOWN");
        } catch (Exception ex) {
            System.err.println("Shutdown failed");
        }
        
    }
    
    @Test
    public void findEmployeeTest(){
        Employee em = new Employee();
        em.setName("Ferdo M");
        EmployeeDAO dao = new EmployeeDAOImpl(emf);
        dao.insert(em);                  
        Employee em1 = dao.getEmployeeById(em.getId());
        assertNotNull(em1);
        assertEquals(em,em1);
        
        em1 = dao.getEmployeeById(1L);
        assertNull(em1);
        //wrong parameters
        try{
            dao.getEmployeeById(null);
            fail("queried with null ID");
        }
        catch(IllegalArgumentException e){}
        
    }
    
    @Test
    public void insertTest(){
        Employee em = new Employee();
        em.setName("Ferdo M");
        EmployeeDAO dao = new EmployeeDAOImpl(emf);
        dao.insert(em);
        assertNotNull(em.getId());                
        Employee em1 = dao.getEmployeeById(em.getId());
        assertEquals(em,em1);
        
        //Wrong inputs
        try{
            dao.insert(null);
            fail("inserted null");
        }
        catch(IllegalArgumentException e){
            
        }
    }
    
    @Test
    public void updateTest(){
        Employee em = new Employee();
        em.setName("Ferdo M");
        EmployeeDAO dao = new EmployeeDAOImpl(emf);
        dao.insert(em);
        em.setPosition("CEO");
        dao.update(em);
        Employee em1 = dao.getEmployeeById(em.getId());
        assertEquals(em1.getPosition(), "CEO");
        
        //Wrong inputs
        try{
            dao.update(null);
            fail("Updated null entity");
        }
        catch(IllegalArgumentException e){}
        
        Employee nullId = new Employee();
        nullId.setPosition("CEO");
        try{
            dao.update(nullId);
            fail("Updated with null ID");
        }
        catch(IllegalArgumentException e){}
        
        
    }
    
    @Test
    public void RemoveTest(){
        Employee em = new Employee();
        em.setName("Ferdo M");
        EmployeeDAO dao = new EmployeeDAOImpl(emf);
        dao.insert(em);
        Employee em1 = dao.getEmployeeById(em.getId());
        Employee em2 = new Employee();
        em2.setName("Maro Potokolo");
        dao.insert(em2);
        Long id = em.getId();
        dao.remove(em);
        assertNull(dao.getEmployeeById(id));
        assertNull(em.getId());
        assertNotNull(dao.getEmployeeById(em2.getId()));
        
        //wrong parameters
        try{
            dao.remove(null);
            fail("Removed null");
        }
        catch(IllegalArgumentException e){}
        
        Employee nullId = new Employee();
        nullId.setPosition("CEO");
        try{
            dao.update(nullId);
            fail("Removed with null ID");
        }
        catch(IllegalArgumentException e){}
        
        
    }
    
    
    
    


}

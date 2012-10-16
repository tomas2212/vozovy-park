
package cz.muni.fi.pa165.vozovypark.DAOTests;


import cz.muni.fi.pa165.vozovypark.DAO.*;
import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
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
        
        em1 = dao.getEmployeeById(34567L);
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
    public void removeTest(){
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
        assertNull(dao.getEmployeeById(em.getId()));
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
    
    @Test
    public void hierarchyTest(){
        CompanyLevel cl1 = new CompanyLevel();
        cl1.setLevelValue(0);
        
        CompanyLevel cl2 = new CompanyLevel();
        cl2.setLevelValue(1);
        
        CompanyLevelDAO clDao = new CompanyLevelDAOImpl(emf);
        clDao.insert(cl1);
        clDao.insert(cl2);
        
        Employee ceo = new Employee();
        ceo.setPosition("CEO");
        ceo.setCompanyLevel(cl1);
        
        Employee assistent = new Employee();
        assistent.setPosition("asistent");
        assistent.setCompanyLevel(cl2);
        
        EmployeeDAO employeeDao = new EmployeeDAOImpl(emf);
        employeeDao.insert(ceo);
        employeeDao.insert(assistent);
        
        Car limo = new Car();
        limo.setCompanyLevel(cl1);
        
        Car skodovka = new Car();
        skodovka.setCompanyLevel(cl2);
        
        CarDAO carDao = new CarDAOImpl(emf);
        carDao.insert(limo);
        carDao.insert(skodovka);
        
        assertEquals(employeeDao.getAllEmployeeWithHigherLevel(cl1).size(), 2);
        assertEquals(employeeDao.getAllEmployeeWithHigherLevel(cl2).size(), 1);
        
        
        
    }
    
    @Test
    public void getAllEmployeesWithHigherLevelTest(){
        CompanyLevel cl = new CompanyLevel();
        cl.setLevelValue(2);
        
        EmployeeDAO employeeDao = new EmployeeDAOImpl(emf);
        CompanyLevelDAO clDao = new CompanyLevelDAOImpl(emf);
        clDao.insert(cl);
        int amount1 = employeeDao.getAllEmployeeWithHigherLevel(cl).size();

        
        CompanyLevel cl1 = new CompanyLevel();
        cl1.setLevelValue(1);
        clDao.insert(cl1);
        
        Employee employee1 = new Employee();
        employee1.setCompanyLevel(cl1);
        employeeDao.insert(employee1);
        
        
        CompanyLevel cl2 = new CompanyLevel();
        cl2.setLevelValue(3);
        clDao.insert(cl2);
        
        Employee employee2 = new Employee();
        employee2.setCompanyLevel(cl2);
        employeeDao.insert(employee2);
        
        int amount2 = employeeDao.getAllEmployeeWithHigherLevel(cl).size();
        
        assertEquals(amount1, amount2-1);
    }
    
    
    
    


}

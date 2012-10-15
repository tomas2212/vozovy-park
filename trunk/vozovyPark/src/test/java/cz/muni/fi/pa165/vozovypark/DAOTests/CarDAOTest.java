/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.DAOTests;


import cz.muni.fi.pa165.vozovypark.DAO.CarDAO;
import cz.muni.fi.pa165.vozovypark.DAO.CarDAOImpl;
import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author acer
 */
public class CarDAOTest {
    private EntityManagerFactory emf;
    private Connection connection;
    
    public CarDAOTest() {
    }

    
    @Before
    public void setUp() {
         try {
            
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:unit-testing-jpa", "sa", "");
        } catch (Exception ex) {
            fail("Exception during HSQL database startup.");
        }
        try {
            
            emf = Persistence.createEntityManagerFactory("testPU");
            
        } catch (Exception ex) {
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
    public void insertTest(){
        Car car1 = new Car();
        car1.setSpz("BR975AM");
        
        CarDAO carDao = new CarDAOImpl(emf);
        carDao.insert(car1);
        
        Car car2 = carDao.getCarById(car1.getId());
        
        assertEquals(car1,car2);
    }
    
    @Test
    public void updateTest() {
        Car car1 = new Car();
        car1.setSpz("BR975AM");
        
        CarDAO carDao = new CarDAOImpl(emf);
        carDao.insert(car1);
        
        Car car2 = carDao.getCarById(car1.getId());
        car2.setSpz("BR976AM");
        carDao.update(car2);
        
        Car car3 = carDao.getCarById(car1.getId());
        
        assertEquals(car1.getSpz(),car2.getSpz());
    }
    
    @Test
    public void deleteTest() {
        Car car1 = new Car();
        car1.setSpz("BR975AM");
        
        CarDAO carDao = new CarDAOImpl(emf);
        carDao.insert(car1);
        
        Car car2 = carDao.getCarById(car1.getId());
        carDao.remove(car2);
        
        assertNull(carDao.getCarById(car1.getId()));
    }
    
    @Test
    public void getAllCarsTest() {
        Car car1 = new Car();
        car1.setSpz("BR975AM");
        
        CarDAO carDao = new CarDAOImpl(emf);
        carDao.insert(car1);
        
        int amount1 = carDao.getAllCars().size();
        
        Car car2 = new Car();
        car2.setSpz("BR976AM");
        carDao.insert(car2);
        
        int amount2 = carDao.getAllCars().size();
        
        assertEquals(amount1, amount2 - 1);
    }
    
    @Test
    public void getCarByIdTest() {
        Car car1 = new Car();
        car1.setSpz("BR975AM");
        
        CarDAO carDao = new CarDAOImpl(emf);
        carDao.insert(car1);
        
        Car car2 = carDao.getCarById(car1.getId());
        
        assertEquals(car1,car2);
    }
    
    @Test
    public void getCarBySpz() {
        String spz = "BR975AM";
        Car car1 = new Car();
        car1.setSpz(spz);
        
        CarDAO carDao = new CarDAOImpl(emf);
        carDao.insert(car1);
        
        Car car2 = carDao.getCarBySpz(spz);
        
        assertEquals(car1,car2);
    }
    
    @Test
    public void getAllCarsWithHigherLevelTest(){
        CompanyLevel cl = new CompanyLevel();
        cl.setLevelValue(2);
        
        CarDAO carDao = new CarDAOImpl(emf);
        int amount1 = carDao.getAllCarsWithHigherLevel(cl).size();
        
        CompanyLevel cl1 = new CompanyLevel();
        cl1.setLevelValue(1);
        
        Car car1 = new Car();
        car1.setCompanyLevel(cl1);
        carDao.insert(car1);
        
        CompanyLevel cl2 = new CompanyLevel();
        cl2.setLevelValue(3);
        
        Car car2 = new Car();
        car2.setCompanyLevel(cl2);
        carDao.insert(car2);
        
        int amount2 = carDao.getAllCarsWithHigherLevel(cl).size();
        
        assertEquals(amount1, amount2-1);
    }
}

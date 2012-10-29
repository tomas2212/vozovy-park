package cz.muni.fi.pa165.vozovypark.DAOTests;

import cz.muni.fi.pa165.vozovypark.DAO.CarDAO;
import cz.muni.fi.pa165.vozovypark.DAO.CarDAOImpl;
import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAO;
import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAOImpl;
import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Eduard Krak
 */
public class CarDAOTest extends AbstractDAOTest{
    @Autowired
    private CarDAO carDao;
    
    @Autowired
    private CompanyLevelDAO companyLevelDao;
    
    @Test
    public void insertTest(){
        Car car1 = new Car();
        car1.setSpz("BR975AM");
        
        
        carDao.insert(car1);
        
        Car car2 = carDao.getCarById(car1.getId());
        
        assertEquals(car1,car2);
        
        try{
            carDao.insert(null);
            fail("Inserted null entity");
        }
        catch(IllegalArgumentException e){}
    }
    
    @Test
    public void updateTest() {
        Car car1 = new Car();
        car1.setSpz("BR975AM");
        
        
        carDao.insert(car1);
        
        Car car2 = carDao.getCarById(car1.getId());
        car2.setSpz("BR976AM");
        carDao.update(car2);
        
        Car car3 = carDao.getCarById(car1.getId());
        
        assertEquals(car3.getSpz(),car2.getSpz());
        
        try{
            carDao.update(null);
            fail("Updated null entity");
        }
        catch(IllegalArgumentException e){}
        
        try{
            Car carWithoutId = new Car();
            carDao.update(carWithoutId);
            fail("Updated entity with null id");
        }
        catch(IllegalArgumentException e){}
    }
    
    @Test
    public void deleteTest() {
        Car car1 = new Car();
        car1.setSpz("BR975AM");
        
        
        carDao.insert(car1);
        
        Car car2 = carDao.getCarById(car1.getId());
        carDao.remove(car2);
        
        assertNull(carDao.getCarById(car1.getId()));
        
        try{
            carDao.remove(null);
            fail("Removed null entity");
        }
        catch(IllegalArgumentException e){}
        
        try{
            Car carWithoutId = new Car();
            carDao.remove(carWithoutId);
            fail("Removed entity with null id");
        }
        catch(IllegalArgumentException e){}
        
    }
    
    @Test
    public void getAllCarsTest() {
        Car car1 = new Car();
        car1.setSpz("BR975AM");
        
        
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
        
        
        carDao.insert(car1);
        
        Car car2 = carDao.getCarById(car1.getId());
        
        assertEquals(car1,car2);
        
        try{
            carDao.getCarById(null);
            fail("Queried with null id");
        }
        catch(IllegalArgumentException e){}
    }
    
    @Test
    public void getCarBySpz()   {
        String spz = "BR975AM";
        Car car1 = new Car();
        car1.setSpz(spz);
        
        
        carDao.insert(car1);
        
        Car car2 = carDao.getCarBySpz(spz);
        
        assertEquals(car1,car2);
        
        try{
            carDao.getCarBySpz(null);
            fail("Queried with null spz");
        }
        catch(IllegalArgumentException e){}
    }
    
    @Test
    public void getAllCarsWithHigherLevelTest(){
        CompanyLevel cl = new CompanyLevel();
        cl.setLevelValue(2);
        
        
       
        companyLevelDao.insert(cl);
        int amount1 = carDao.getAllCarsWithHigherLevel(cl).size();

        
        CompanyLevel cl1 = new CompanyLevel();
        cl1.setLevelValue(1);
        companyLevelDao.insert(cl1);
        
        Car car1 = new Car();
        car1.setCompanyLevel(cl1);
        carDao.insert(car1);
        
        
        CompanyLevel cl2 = new CompanyLevel();
        cl2.setLevelValue(3);
        companyLevelDao.insert(cl2);
        
        Car car2 = new Car();
        car2.setCompanyLevel(cl2);
        carDao.insert(car2);
        
        int amount2 = carDao.getAllCarsWithHigherLevel(cl).size();
        
        assertEquals(amount1, amount2-1);
        
        try{
            carDao.getAllCarsWithHigherLevel(null);
            fail("Queried with null company level");
        }
        catch(IllegalArgumentException e){}
        
        try{
            CompanyLevel clWithoutId = new CompanyLevel();
            carDao.getAllCarsWithHigherLevel(clWithoutId);
            fail("Queried entity with null id");
        }
        catch(IllegalArgumentException e){}
    }
}

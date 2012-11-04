/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.ServiceTests;

import cz.muni.fi.pa165.vozovypark.DAO.CarDAO;
import cz.muni.fi.pa165.vozovypark.DTO.CarDTO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.service.CarServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
/**
 *
 * @author Eduard Krak
 */
@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {
    
    @Mock
    private CarDAO carDao;
    @InjectMocks
    private CarServiceImpl carService;
    
    @Test
    public void testCreateCar() {

        CarDTO carDto = new CarDTO();
        carDto.setBrand("Volkswagen");

        
        carService.createCar(carDto);
        verify(carDao, times(1)).insert(any(Car.class));

        try {
            carService.createCar(null);
            fail("accepted null value");
        } catch (IllegalArgumentException e) {
        }

        //testing if employeDao was called before throwing exception
        verify(carDao, never()).insert(null);

    }
    
    @Test
    public void testUpdateCar() {
        CarDTO carDto = new CarDTO();
        carDto.setModel("Volkswagen");
        carDto.setId(new Long(1));

        CarDTO noIdDto = new CarDTO();
        carDto.setModel("Volkswagen");

        carService.updateCar(carDto);
        verify(carDao, times(1)).update(any(Car.class));

        try {
            carService.createCar(null);
            fail("Implementation accepted null value");
        } catch (IllegalArgumentException e) {
        }

        //testing if employeDao was called before throwing exception
        verify(carDao, never()).update(null);

        try {
            carService.createCar(noIdDto);
            fail("Implementation accepted no id");
        } catch (IllegalArgumentException e) {
        }
    }
    
    /*
    @Test
    public void testSetCarAvailable() {
        CarDTO carDto = new CarDTO();
        carDto.setModel("Volkswagen");
        carDto.setAvailable(Boolean.FALSE);

        carDto = carService.updateCar(carDto);
        verify(carDao, times(1)).update(any(Car.class));

        try {
            carService.createCar(null);
            fail("Implementation accepted null value");
        } catch (IllegalArgumentException e) {
        }

        //testing if employeDao was called before throwing exception
        verify(carDao, never()).update(null);

        try {
            carService.createCar(noIdDto);
            fail("Implementation accepted no id");
        } catch (IllegalArgumentException e) {
        }
    }
    */
    @Test
    public void testGetCarById() {
        Car car = new Car();
        car.setId(new Long(1));
        car.setModel("Volkswagen");

        CarDTO dto = new CarDTO();
        dto.setId(new Long(1));
        dto.setModel("Volkswagen");

        when(carDao.getCarById(new Long(1))).thenReturn(car);
        CarDTO employeeById = carService.getCarById(new Long(1));
        assertEquals( dto, employeeById);
        
        verify(carDao, times(1)).getCarById(eq(new Long(1)));

        try {
            carService.getCarById(null);
            fail("accepted null id");

        } catch (IllegalArgumentException e) {
        }

        when(carDao.getCarById(eq(new Long(2)))).thenReturn(null);
        assertNull(carService.getCarById(new Long(2)));
    }
    
     @Test
    public void testGetAllCars() {
        Car car1 = new Car();
        car1.setId(new Long(1));
        car1.setModel("Volkswagen");

        Car car2 = new Car();
        car2.setId(new Long(2));
        car2.setModel("Mercedes");

        Car car3 = new Car();
        car3.setId(new Long(3));
        car3.setModel("Skoda");

        CarDTO car1dto = new CarDTO();
        car1dto.setId(new Long(1));
        car1dto.setModel("Volkswagen");

        CarDTO car2dto = new CarDTO();
        car2dto.setId(new Long(2));
        car2dto.setModel("Mercedes");

        CarDTO car3dto = new CarDTO();
        car3dto.setId(new Long(3));
        car3dto.setModel("Skoda");
        
        List<Car> allEntities = new ArrayList<Car>();
        allEntities.add(car1);
        allEntities.add(car2);
        allEntities.add(car3);
        
        List<CarDTO> allDTO = new ArrayList<CarDTO>();
        allDTO.add(car1dto);
        allDTO.add(car2dto);
        allDTO.add(car3dto);
        
        when(carDao.getAllCars()).thenReturn(allEntities);
        List<CarDTO> returnedCars = carService.getAllCars();
        assertEquals(allDTO.size(), returnedCars.size());
        for(int i = 0; i < returnedCars.size(); i++){
            assertEquals(allDTO.get(i), returnedCars.get(i));
        }
        verify(carDao, times(1)).getAllCars();
        
    }
     
     @Test
     public void testGetCarsByCompanyLevel() {
        CompanyLevel cl1 = new CompanyLevel();
        cl1.setLevelValue(1);
        cl1.setId(new Long(1));
        
        CompanyLevel cl2 = new CompanyLevel();
        cl2.setLevelValue(2);
        cl2.setId(new Long(2));
        
        CompanyLevel cl3 = new CompanyLevel();
        cl3.setLevelValue(3);
        cl3.setId(new Long(3));
        
        CompanyLevelDTO cl1dto = new CompanyLevelDTO();
        cl1dto.setLevelValue(1);
        cl1dto.setId(new Long(1));
        
        CompanyLevelDTO cl2dto = new CompanyLevelDTO();
        cl2dto.setLevelValue(2);
        cl2dto.setId(new Long(2));
        
        CompanyLevelDTO cl3dto = new CompanyLevelDTO();
        cl3dto.setLevelValue(3);
        cl3dto.setId(new Long(3));
        
        
         Car car1 = new Car();
        car1.setId(new Long(1));
        car1.setModel("Volkswagen");

        Car car2 = new Car();
        car2.setId(new Long(2));
        car2.setModel("Mercedes");

        Car car3 = new Car();
        car3.setId(new Long(3));
        car3.setModel("Skoda");
        
        List<Car> allEntities = new ArrayList<Car>();
        allEntities.add(car1);
        allEntities.add(car2);
        allEntities.add(car3);
        
        List<Car> cl2Entities = new ArrayList<Car>();
        cl2Entities.add(car2);
        cl2Entities.add(car3);
         
        when(carDao.getAllCars()).thenReturn(allEntities); //if in some case when implementation wants to call it
        when(carDao.getAllCarsWithHigherLevel(eq(cl3))).thenReturn(cl2Entities);
        List<CarDTO> returnedEmployees = carService.getCarsByCompanyLevel(cl2dto);
        assertEquals(cl2Entities.size(), returnedEmployees.size());
        for(CarDTO em : returnedEmployees){
            assertTrue(em.getCompanyLevel().getLevelValue() >= cl2dto.getLevelValue());
        }
        
        try{
            carService.getCarsByCompanyLevel(null);
            fail("Implementation accepts null value of company Level");
        }
        catch(IllegalArgumentException e){}
     }
    
}

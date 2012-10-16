/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.DAOTests;

import cz.muni.fi.pa165.vozovypark.DAO.CarDAO;
import cz.muni.fi.pa165.vozovypark.DAO.CarDAOImpl;
import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAO;
import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAOImpl;
import cz.muni.fi.pa165.vozovypark.DAO.EmployeeDAO;
import cz.muni.fi.pa165.vozovypark.DAO.EmployeeDAOImpl;
import cz.muni.fi.pa165.vozovypark.DAO.ReservationDAO;
import cz.muni.fi.pa165.vozovypark.DAO.ReservationDAOImpl;
import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import cz.muni.fi.pa165.vozovypark.entities.Reservation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomas Svrcek
 */
public class ReservationDAOTest {

    private EntityManagerFactory emf;

    public ReservationDAOTest() {
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
    public void insertTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");

        Employee employee = new Employee();
        CompanyLevel cl = new CompanyLevel();
        cl.setLevelValue(1);
        employee.setCompanyLevel(cl);
        employee.setName("Tomas");
        employee.setAddress("Zilina");

        Reservation reservation = new Reservation();
        if (car.getAvailable()){
        reservation.setCar(car);
        }
        reservation.setEmployee(employee);

        ReservationDAO rdao = new ReservationDAOImpl(emf);
        rdao.insert(reservation);
        Reservation reservation2 = rdao.getReservationById(reservation.getId());
        assertEquals(reservation, reservation2);
    }

    @Test
    public void updateTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");

        Car car2 = new Car();
        car2.setAvailable(true);
        car2.setBrand("Audi");

        Employee employee = new Employee();
        CompanyLevel cl = new CompanyLevel();
        cl.setLevelValue(1);
        employee.setCompanyLevel(cl);
        employee.setName("Tomas");
        employee.setAddress("Zilina");

        Reservation reservation = new Reservation();
        if (car.getAvailable()){
        reservation.setCar(car);
        }
        reservation.setEmployee(employee);

        ReservationDAO rdao = new ReservationDAOImpl(emf);
        rdao.insert(reservation);
        if (car.getAvailable()){
        reservation.setCar(car2);
        }
        rdao.update(reservation);
        Reservation reservation2 = rdao.getReservationById(reservation.getId());
        assertEquals(reservation, reservation2);
    }

    @Test
    public void removeTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");

        Employee employee = new Employee();
        CompanyLevel cl = new CompanyLevel();
        cl.setLevelValue(1);
        employee.setCompanyLevel(cl);
        employee.setName("Tomas");
        employee.setAddress("Zilina");

        Reservation reservation = new Reservation();
        if (car.getAvailable()){
        reservation.setCar(car);
        }
        reservation.setEmployee(employee);

        ReservationDAO rdao = new ReservationDAOImpl(emf);
        rdao.insert(reservation);
        Reservation reservation2 = rdao.getReservationById(reservation.getId());
        rdao.remove(reservation2);
        assertNull(rdao.getReservationById(reservation.getId()));
    }

    @Test
    public void getReservationByCarTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");

        Employee employee = new Employee();
        CompanyLevel cl = new CompanyLevel();
        cl.setLevelValue(1);
        employee.setCompanyLevel(cl);
        employee.setName("Tomas");
        employee.setAddress("Zilina");

        Reservation reservation = new Reservation();
        if (car.getAvailable()){
        reservation.setCar(car);
        }
        reservation.setEmployee(employee);
        
        ReservationDAO rdao = new ReservationDAOImpl(emf);
        rdao.insert(reservation);

        Employee employee2 = new Employee();
        CompanyLevel cl2 = new CompanyLevel();
        cl2.setLevelValue(2);
        employee2.setCompanyLevel(cl2);
        employee2.setName("Martin");
        employee2.setAddress("Brno");

        Reservation reservation2 = new Reservation();
        if (car.getAvailable()){
        reservation2.setCar(car);
        }
        reservation2.setEmployee(employee2);
        
        rdao.insert(reservation2);
        
        List<Reservation> list = rdao.getReservationByCar(car);
        
        assertEquals(2, list.size());  
    }
    
        public void getReservationByEmployeeTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");

        Employee employee = new Employee();
        CompanyLevel cl = new CompanyLevel();
        cl.setLevelValue(1);
        employee.setCompanyLevel(cl);
        employee.setName("Tomas");
        employee.setAddress("Zilina");

        Reservation reservation = new Reservation();
        if (car.getAvailable()){
        reservation.setCar(car);
        }
        reservation.setEmployee(employee);
        
        ReservationDAO rdao = new ReservationDAOImpl(emf);
        rdao.insert(reservation);

        Car car2 = new Car();
        CompanyLevel cl2 = new CompanyLevel();
        cl2.setLevelValue(2);
        car2.setAvailable(true);
        car2.setBrand("Audi");
        car2.setSpz("Ahoj-ko");

        Reservation reservation2 = new Reservation();
        if (car2.getAvailable()){
        reservation2.setCar(car2);
        }
        reservation2.setEmployee(employee);
        
        rdao.insert(reservation2);
        
        List<Reservation> list = rdao.getReservationByEmployee(employee);
        
        assertEquals(2, list.size());  
    }
    
    @Test
    public void getReservationByCarAndEmployeeTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");
        
        CarDAO carDao = new CarDAOImpl(emf);
        carDao.insert(car);

        Employee employee = new Employee();
        CompanyLevel cl = new CompanyLevel();
        cl.setLevelValue(1);
        employee.setCompanyLevel(cl);
        employee.setName("Tomas");
        employee.setAddress("Zilina");
        
        EmployeeDAO eDao = new EmployeeDAOImpl(emf);
        eDao.insert(employee);

        Reservation reservation = new Reservation();
        if (car.getAvailable()){
        reservation.setCar(car);
        }
        reservation.setEmployee(employee);
       
        Employee employee2 = new Employee();
        CompanyLevel cl2 = new CompanyLevel();
        cl2.setLevelValue(2);
        employee2.setCompanyLevel(cl2);
        employee2.setName("Martin");
        employee2.setAddress("Brno");
        
        eDao.insert(employee2);

        Reservation reservation2 = new Reservation();
        if (car.getAvailable()){
        reservation2.setCar(car);
        }
        reservation2.setEmployee(employee2);
        
        ReservationDAO rdao = new ReservationDAOImpl(emf);
        rdao.insert(reservation);
        rdao.insert(reservation2);
        
        assertEquals(reservation, rdao.getReservationByCarAndEmployee(car, employee));
    }
    
    @Test
    public void getAllReservationsTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");
        
        CarDAO carDao = new CarDAOImpl(emf);
        carDao.insert(car);

        Employee employee = new Employee();
        CompanyLevel cl = new CompanyLevel();
        cl.setLevelValue(1);
        employee.setCompanyLevel(cl);
        employee.setName("Tomas");
        employee.setAddress("Zilina");
        
        EmployeeDAO eDao = new EmployeeDAOImpl(emf);
        eDao.insert(employee);

        Reservation reservation = new Reservation();
        if (car.getAvailable()){
        reservation.setCar(car);
        }
        reservation.setEmployee(employee);
        
        ReservationDAO rdao = new ReservationDAOImpl(emf);
        rdao.insert(reservation);

        Car car2 = new Car();
        CompanyLevel cl2 = new CompanyLevel();
        cl2.setLevelValue(2);
        car2.setAvailable(true);
        car2.setBrand("Audi");
        car2.setSpz("Ahoj-ko");
        
        carDao.insert(car2);

        Reservation reservation2 = new Reservation();
        if (car2.getAvailable()){
        reservation2.setCar(car2);
        }
        reservation2.setEmployee(employee);
        
        rdao.insert(reservation2);
        
        List<Reservation> list = rdao.getAllReservations();
        
        assertEquals(2, list.size());        
    }   
}

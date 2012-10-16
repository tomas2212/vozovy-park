package cz.muni.fi.pa165.vozovypark.DAOTests;

import cz.muni.fi.pa165.vozovypark.DAO.CarDAO;
import cz.muni.fi.pa165.vozovypark.DAO.CarDAOImpl;
import cz.muni.fi.pa165.vozovypark.DAO.EmployeeDAO;
import cz.muni.fi.pa165.vozovypark.DAO.EmployeeDAOImpl;
import cz.muni.fi.pa165.vozovypark.DAO.ReservationDAO;
import cz.muni.fi.pa165.vozovypark.DAO.ReservationDAOImpl;
import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import cz.muni.fi.pa165.vozovypark.entities.Reservation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Tomas Svrcek
 */
public class ReservationDAOTest {

    private EntityManagerFactory emf;
    private Connection connection;

    public ReservationDAOTest() {
    }

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

        CarDAO cDao = new CarDAOImpl(emf);
        cDao.insert(car);

        Employee employee = new Employee();
        employee.setName("Tomas");
        employee.setAddress("Zilina");

        EmployeeDAO eDao = new EmployeeDAOImpl(emf);
        eDao.insert(employee);

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setEmployee(employee);

        ReservationDAO rdao = new ReservationDAOImpl(emf);
        rdao.insert(reservation);
        Reservation reservation2 = rdao.getReservationById(reservation.getId());
        assertEquals(reservation, reservation2);

        try {
            rdao.insert(null);
            fail("Inserted null entity");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void updateTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");

        CarDAO cDao = new CarDAOImpl(emf);
        cDao.insert(car);

        Car car2 = new Car();
        car2.setAvailable(true);
        car2.setBrand("Audi");

        cDao.insert(car2);

        Employee employee = new Employee();
        employee.setName("Tomas");
        employee.setAddress("Zilina");

        EmployeeDAO eDao = new EmployeeDAOImpl(emf);
        eDao.insert(employee);

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setEmployee(employee);

        ReservationDAO rdao = new ReservationDAOImpl(emf);
        rdao.insert(reservation);

        reservation.setCar(car2);
        rdao.update(reservation);
        Reservation reservation2 = rdao.getReservationById(reservation.getId());
        assertEquals(reservation, reservation2);

        try {
            rdao.update(null);
            fail("Updated null entity");
        } catch (IllegalArgumentException e) {
        }

        try {
            Reservation rWithoutId = new Reservation();
            rdao.update(rWithoutId);
            fail("Updated entity with null id");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void removeTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");

        CarDAO cDao = new CarDAOImpl(emf);
        cDao.insert(car);

        Employee employee = new Employee();
        employee.setName("Tomas");
        employee.setAddress("Zilina");

        EmployeeDAO eDao = new EmployeeDAOImpl(emf);
        eDao.insert(employee);

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setEmployee(employee);

        ReservationDAO rdao = new ReservationDAOImpl(emf);
        rdao.insert(reservation);

        Reservation reservation2 = rdao.getReservationById(reservation.getId());
        rdao.remove(reservation2);
        assertNull(rdao.getReservationById(reservation.getId()));

        try {
            rdao.remove(null);
            fail("Removed null entity");
        } catch (IllegalArgumentException e) {
        }

        try {
            Reservation rWithoutId = new Reservation();
            rdao.remove(rWithoutId);
            fail("Removed entity with null id");
        } catch (IllegalArgumentException e) {
        }


    }

    @Test
    public void getReservationByCarTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");

        CarDAO cDao = new CarDAOImpl(emf);
        cDao.insert(car);

        Employee employee = new Employee();
        employee.setName("Tomas");
        employee.setAddress("Zilina");

        EmployeeDAO eDao = new EmployeeDAOImpl(emf);
        eDao.insert(employee);

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setEmployee(employee);

        ReservationDAO rdao = new ReservationDAOImpl(emf);
        rdao.insert(reservation);

        Employee employee2 = new Employee();
        employee2.setName("Martin");
        employee2.setAddress("Brno");

        eDao.insert(employee2);

        Reservation reservation2 = new Reservation();
        reservation2.setCar(car);
        reservation2.setEmployee(employee2);

        rdao.insert(reservation2);

        List<Reservation> list = rdao.getReservationByCar(car);

        assertEquals(2, list.size());

        try {
            rdao.getReservationByCar(null);
            fail("You cant find reservation by car as null");
        } catch (IllegalArgumentException e) {
        }

        try {
            Car carWithoutId = new Car();
            rdao.getReservationByCar(carWithoutId);
            fail("You cant find reservation by car without id");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void getReservationByEmployeeTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");

        CarDAO cDao = new CarDAOImpl(emf);
        cDao.insert(car);

        Employee employee = new Employee();
        employee.setName("Tomas");
        employee.setAddress("Zilina");

        EmployeeDAO eDao = new EmployeeDAOImpl(emf);
        eDao.insert(employee);

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setEmployee(employee);

        ReservationDAO rdao = new ReservationDAOImpl(emf);
        rdao.insert(reservation);

        Car car2 = new Car();
        car2.setAvailable(true);
        car2.setBrand("Audi");
        car2.setSpz("Ahoj-ko");


        cDao.insert(car2);

        Reservation reservation2 = new Reservation();
        reservation2.setCar(car2);
        reservation2.setEmployee(employee);

        rdao.insert(reservation2);

        List<Reservation> list = rdao.getReservationByEmployee(employee);

        assertEquals(2, list.size());

        try {
            rdao.getReservationByEmployee(null);
            fail("You cant find reservation by employee as null");
        } catch (IllegalArgumentException e) {
        }

        try {
            Employee empWithoutId = new Employee();
            rdao.getReservationByEmployee(empWithoutId);
            fail("You cant find reservation by employee without id");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void getReservationByCarAndEmployeeTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");

        CarDAO carDao = new CarDAOImpl(emf);
        carDao.insert(car);

        Employee employee = new Employee();
        employee.setName("Tomas");
        employee.setAddress("Zilina");

        EmployeeDAO eDao = new EmployeeDAOImpl(emf);
        eDao.insert(employee);

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setEmployee(employee);

        ReservationDAO rdao = new ReservationDAOImpl(emf);
        rdao.insert(reservation);

        Employee employee2 = new Employee();
        employee2.setName("Martin");
        employee2.setAddress("Brno");

        eDao.insert(employee2);

        Reservation reservation2 = new Reservation();
        reservation2.setCar(car);
        reservation2.setEmployee(employee2);

        rdao.insert(reservation2);

        assertTrue(rdao.getReservationByCarAndEmployee(car, employee).contains(reservation));

        try {
            rdao.getReservationByCarAndEmployee(null, null);
            fail("You cant find reservation by car as null and employee as null");
        } catch (IllegalArgumentException e) {
        }

        try {
            rdao.getReservationByCarAndEmployee(null, employee);
            fail("You can't find reservation by employee when car is null");
        } catch (IllegalArgumentException e) {
        }

        try {
            rdao.getReservationByCarAndEmployee(car, null);
            fail("You can't find reservation by employee when employee is null");
        } catch (IllegalArgumentException e) {
        }

        try {
            Car carWithoutId = new Car();
            Employee employeeWithoutId = new Employee();
            rdao.getReservationByCarAndEmployee(carWithoutId, employeeWithoutId);
            fail("You cant find reservation by car and employee with null ids");
        } catch (IllegalArgumentException e) {
        }

        try {
            rdao.getReservationByCarAndEmployee(new Car(), new Employee());
            fail("Missing employee ID and car ID");
        } catch (IllegalArgumentException e) {
        }

    }

    @Test
    public void getAllReservationsTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");

        CarDAO carDao = new CarDAOImpl(emf);
        carDao.insert(car);

        Employee employee = new Employee();
        employee.setName("Tomas");
        employee.setAddress("Zilina");

        EmployeeDAO eDao = new EmployeeDAOImpl(emf);
        eDao.insert(employee);

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setEmployee(employee);

        ReservationDAO rdao = new ReservationDAOImpl(emf);
        rdao.insert(reservation);

        Car car2 = new Car();
        car2.setAvailable(true);
        car2.setBrand("Audi");
        car2.setSpz("Ahoj-ko");

        carDao.insert(car2);

        Reservation reservation2 = new Reservation();
        reservation2.setCar(car2);
        reservation2.setEmployee(employee);

        rdao.insert(reservation2);

        List<Reservation> list = rdao.getAllReservations();

        assertEquals(2, list.size());
    }
}

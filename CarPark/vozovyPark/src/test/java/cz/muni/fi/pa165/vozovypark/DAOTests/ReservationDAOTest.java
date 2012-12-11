package cz.muni.fi.pa165.vozovypark.DAOTests;

import cz.muni.fi.pa165.vozovypark.DAO.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Tomas Svrcek
 */
public class ReservationDAOTest extends AbstractDAOTest {

    @Autowired
    private CarDAO carDao;
    @Autowired
    private EmployeeDAO employeeDao;
    @Autowired
    private ReservationDAO reservationDao;

    @Test
    public void insertTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");
        carDao.insert(car);

        Employee employee = new Employee();
        employee.setName("Tomas");
        employee.setAddress("Zilina");
        employeeDao.insert(employee);

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setEmployee(employee);
        reservationDao.insert(reservation);

        Reservation reservation2 = reservationDao.getReservationById(reservation.getId());
        assertEquals(reservation, reservation2);

        try {
            reservationDao.insert(null);
            fail("Inserted null entity");
        } catch (DataAccessException e) {
        }
    }

    @Test
    public void updateTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");
        carDao.insert(car);

        Car car2 = new Car();
        car2.setAvailable(true);
        car2.setBrand("Audi");
        carDao.insert(car2);

        Employee employee = new Employee();
        employee.setName("Tomas");
        employee.setAddress("Zilina");
        employeeDao.insert(employee);

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setEmployee(employee);
        reservationDao.insert(reservation);

        reservation.setCar(car2);
        reservationDao.update(reservation);
        Reservation reservation2 = reservationDao.getReservationById(reservation.getId());
        assertEquals(reservation, reservation2);

        try {
            reservationDao.update(null);
            fail("Updated null entity");
        } catch (DataAccessException e) {
        }

        try {
            Reservation rWithoutId = new Reservation();
            reservationDao.update(rWithoutId);
            fail("Updated entity with null id");
        } catch (DataAccessException e) {
        }
    }

    @Test
    public void removeTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");
        carDao.insert(car);

        Employee employee = new Employee();
        employee.setName("Tomas");
        employee.setAddress("Zilina");
        employeeDao.insert(employee);

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setEmployee(employee);
        reservationDao.insert(reservation);

        Reservation reservation2 = reservationDao.getReservationById(reservation.getId());
        reservationDao.remove(reservation2);
        assertNull(reservationDao.getReservationById(reservation.getId()));

        try {
            reservationDao.remove(null);
            fail("Removed null entity");
        } catch (DataAccessException e) {
        }

        try {
            Reservation rWithoutId = new Reservation();
            reservationDao.remove(rWithoutId);
            fail("Removed entity with null id");
        } catch (DataAccessException e) {
        }


    }

    @Test
    public void getReservationByCarTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");
        carDao.insert(car);

        Employee employee = new Employee();
        employee.setName("Tomas");
        employee.setAddress("Zilina");
        employeeDao.insert(employee);

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setEmployee(employee);
        reservationDao.insert(reservation);

        Employee employee2 = new Employee();
        employee2.setName("Martin");
        employee2.setAddress("Brno");
        employeeDao.insert(employee2);

        Reservation reservation2 = new Reservation();
        reservation2.setCar(car);
        reservation2.setEmployee(employee2);
        reservationDao.insert(reservation2);

        List<Reservation> list = reservationDao.getReservationByCar(car);

        assertEquals(2, list.size());

        try {
            reservationDao.getReservationByCar(null);
            fail("You cant find reservation by car as null");
        } catch (DataAccessException e) {
        }

        try {
            Car carWithoutId = new Car();
            reservationDao.getReservationByCar(carWithoutId);
            fail("You cant find reservation by car without id");
        } catch (DataAccessException e) {
        }
    }

    @Test
    public void getReservationByEmployeeTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");
        carDao.insert(car);

        Employee employee = new Employee();
        employee.setName("Tomas");
        employee.setAddress("Zilina");
        employeeDao.insert(employee);

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setEmployee(employee);
        reservationDao.insert(reservation);

        Car car2 = new Car();
        car2.setAvailable(true);
        car2.setBrand("Audi");
        car2.setSpz("Ahoj-ko");
        carDao.insert(car2);

        Reservation reservation2 = new Reservation();
        reservation2.setCar(car2);
        reservation2.setEmployee(employee);

        reservationDao.insert(reservation2);

        List<Reservation> list = reservationDao.getReservationByEmployee(employee);

        assertEquals(2, list.size());

        try {
            reservationDao.getReservationByEmployee(null);
            fail("You cant find reservation by employee as null");
        } catch (DataAccessException e) {
        }

        try {
            Employee empWithoutId = new Employee();
            reservationDao.getReservationByEmployee(empWithoutId);
            fail("You cant find reservation by employee without id");
        } catch (DataAccessException e) {
        }
    }

    @Test
    public void getReservationByCarAndEmployeeTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");
        carDao.insert(car);

        Employee employee = new Employee();
        employee.setName("Tomas");
        employee.setAddress("Zilina");
        employeeDao.insert(employee);

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setEmployee(employee);
        reservationDao.insert(reservation);

        Employee employee2 = new Employee();
        employee2.setName("Martin");
        employee2.setAddress("Brno");
        employeeDao.insert(employee2);

        Reservation reservation2 = new Reservation();
        reservation2.setCar(car);
        reservation2.setEmployee(employee2);
        reservationDao.insert(reservation2);

        assertTrue(reservationDao.getReservationByCarAndEmployee(car, employee).contains(reservation));

        try {
            reservationDao.getReservationByCarAndEmployee(null, null);
            fail("You cant find reservation by car as null and employee as null");
        } catch (DataAccessException e) {
        }

        try {
            reservationDao.getReservationByCarAndEmployee(null, employee);
            fail("You can't find reservation by employee when car is null");
        } catch (DataAccessException e) {
        }

        try {
            reservationDao.getReservationByCarAndEmployee(car, null);
            fail("You can't find reservation by employee when employee is null");
        } catch (DataAccessException e) {
        }

        try {
            Car carWithoutId = new Car();
            Employee employeeWithoutId = new Employee();
            reservationDao.getReservationByCarAndEmployee(carWithoutId, employeeWithoutId);
            fail("You cant find reservation by car and employee with null ids");
        } catch (DataAccessException e) {
        }

        try {
            reservationDao.getReservationByCarAndEmployee(new Car(), new Employee());
            fail("Missing employee ID and car ID");
        } catch (DataAccessException e) {
        }

    }

    @Test
    public void getAllReservationsTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");
        carDao.insert(car);

        Employee employee = new Employee();
        employee.setName("Tomas");
        employee.setAddress("Zilina");
        employeeDao.insert(employee);

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setEmployee(employee);
        reservationDao.insert(reservation);

        Car car2 = new Car();
        car2.setAvailable(true);
        car2.setBrand("Audi");
        car2.setSpz("Ahoj-ko");
        carDao.insert(car2);

        Reservation reservation2 = new Reservation();
        reservation2.setCar(car2);
        reservation2.setEmployee(employee);

        reservationDao.insert(reservation2);

        List<Reservation> list = reservationDao.getAllReservations();

        assertEquals(2, list.size());
    }

    @Test
    public void getReservationByIdTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");
        carDao.insert(car);

        Employee employee = new Employee();
        employee.setName("Tomas");
        employee.setAddress("Zilina");
        employeeDao.insert(employee);

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setEmployee(employee);
        reservationDao.insert(reservation);

        Reservation reservation2 = reservationDao.getReservationById(reservation.getId());

        assertEquals(reservation, reservation2);

        try {
            reservationDao.getReservationById(null);
            fail("Queried with null id");
        } catch (DataAccessException e) {
        }
    }

    @Test
    public void getReservationsToConfirmTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");
        carDao.insert(car);

        Employee employee = new Employee();
        employee.setName("Tomas");
        employee.setAddress("Zilina");
        employeeDao.insert(employee);

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setEmployee(employee);
        reservation.setConfirmed(true);
        reservationDao.insert(reservation);

        Employee employee2 = new Employee();
        employee2.setName("Martin");
        employee2.setAddress("Brno");
        employeeDao.insert(employee2);

        Reservation reservation2 = new Reservation();
        reservation2.setCar(car);
        reservation2.setEmployee(employee2);
        reservation2.setConfirmed(false);
        reservationDao.insert(reservation2);

        List<Reservation> list = reservationDao.getReservationsToConfirm();

        assertEquals(1, list.size());
    }

    @Test
    public void getAcceptedReservationsTest() {
        Car car = new Car();
        car.setAvailable(true);
        car.setBrand("Mercedes");
        carDao.insert(car);

        Employee employee = new Employee();
        employee.setName("Tomas");
        employee.setAddress("Zilina");
        employeeDao.insert(employee);

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setEmployee(employee);
        reservation.setConfirmed(true);
        reservationDao.insert(reservation);

        Employee employee2 = new Employee();
        employee2.setName("Martin");
        employee2.setAddress("Brno");
        employeeDao.insert(employee2);

        Reservation reservation2 = new Reservation();
        reservation2.setCar(car);
        reservation2.setEmployee(employee2);
        reservation2.setConfirmed(false);
        reservationDao.insert(reservation2);

        List<Reservation> list = reservationDao.getAcceptedReservations();
        Reservation reservation3 = list.get(0);
       
        assertEquals(reservation, reservation3);   
    }
}

package cz.muni.fi.pa165.vozovypark.ServiceTests;

import cz.muni.fi.pa165.vozovypark.DAO.ReservationDAO;
import cz.muni.fi.pa165.vozovypark.DTO.CarDTO;
import cz.muni.fi.pa165.vozovypark.DTO.EmployeeDTO;
import cz.muni.fi.pa165.vozovypark.DTO.ReservationDTO;
import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import cz.muni.fi.pa165.vozovypark.entities.Reservation;
import cz.muni.fi.pa165.vozovypark.service.ReservationServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

/**
 *
 * @author Tomas
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceJUnitTest {

    @Mock
    private ReservationDAO reservationDao;
    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Test
    public void testCreateReservation() {

        EmployeeDTO employeeDto = new EmployeeDTO();
        employeeDto.setName("Fero Pouker");

        CarDTO carDto = new CarDTO();
        carDto.setSpz("ZA979AZ");

        ReservationDTO resDto = new ReservationDTO();
        resDto.setCar(carDto);
        resDto.setEmployee(employeeDto);

        ReservationDTO res2Dto = new ReservationDTO();
        res2Dto.setEmployee(employeeDto);


        reservationService.createReservation(resDto);
        verify(reservationDao, times(1)).insert(any(Reservation.class));

        try {
            reservationService.createReservation(null);
            fail("accepted null value");
        } catch (IllegalArgumentException e) {
        }

        //testing if reservationDao was called before throwing exception
        verify(reservationDao, never()).insert(null);

        try {
            reservationService.createReservation(res2Dto);
            fail("accepted no Car");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testUpdateReservation() {

        EmployeeDTO employeeDto = new EmployeeDTO();
        employeeDto.setName("Fero Parris");
        employeeDto.setId(new Long(1));

        CarDTO carDto = new CarDTO();
        carDto.setSpz("ZA979AZ");

        ReservationDTO resDto = new ReservationDTO();
        resDto.setCar(carDto);
        resDto.setEmployee(employeeDto);

        ReservationDTO res2Dto = new ReservationDTO();
        res2Dto.setEmployee(employeeDto);


        reservationService.updateReservation(resDto);
        verify(reservationDao, times(1)).update(any(Reservation.class));

        try {
            reservationService.createReservation(null);
            fail("Implementation accepted null value");
        } catch (IllegalArgumentException e) {
        }

        //testing if reservationDao was called before throwing exception
        verify(reservationDao, never()).update(null);

        try {
            reservationService.createReservation(res2Dto);
            fail("Implementation accepted but was no car");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testGetReservationById() {
        Employee employee = new Employee();
        employee.setName("Johny Star");

        Car car = new Car();
        car.setSpz("ahoja");

        Reservation reservation = new Reservation();
        reservation.setId(new Long(1));
        reservation.setCar(car);
        reservation.setEmployee(employee);


        EmployeeDTO edto = new EmployeeDTO();
        edto.setName("Johny Star");

        CarDTO cdto = new CarDTO();
        cdto.setSpz("ahoja");

        ReservationDTO rdto = new ReservationDTO();
        rdto.setId(new Long(1));
        rdto.setCar(cdto);
        rdto.setEmployee(edto);

        when(reservationDao.getReservationById(new Long(1))).thenReturn(reservation);

        ReservationDTO reservationById = reservationService.getReservationById(new Long(1));
        assertEquals(rdto, reservationById);

        verify(reservationDao, times(1)).getReservationById(eq(new Long(1)));

        try {
            reservationService.getReservationById(null);
            fail("accepted null id");

        } catch (IllegalArgumentException e) {
        }

        when(reservationDao.getReservationById(eq(new Long(2)))).thenReturn(null);
        assertNull(reservationService.getReservationById(new Long(2)));
    }

    @Test
    public void testGetAllReservations() {
        Employee employee1 = new Employee();
        employee1.setId(new Long(1));
        employee1.setName("Johny Bravo");

        Car car1 = new Car();
        car1.setSpz("CA123GD");

        Reservation reservation1 = new Reservation();
        reservation1.setCar(car1);
        reservation1.setEmployee(employee1);


        Employee employee2 = new Employee();
        employee2.setId(new Long(2));
        employee2.setName("Silvester Vlkoucho");

        Car car2 = new Car();
        car2.setSpz("BA125EX");

        Reservation reservation2 = new Reservation();
        reservation2.setCar(car2);
        reservation2.setEmployee(employee2);


        Employee employee3 = new Employee();
        employee3.setId(new Long(3));
        employee3.setName("Margareta Svietislnkova");

        Car car3 = new Car();
        car3.setSpz("BC234EX");

        Reservation reservation3 = new Reservation();
        reservation3.setCar(car3);
        reservation3.setEmployee(employee3);




        EmployeeDTO employee1dto = new EmployeeDTO();
        employee1dto.setId(new Long(1));
        employee1dto.setName("Johny Bravo");

        CarDTO car1dto = new CarDTO();
        car1dto.setSpz("CA123GD");

        ReservationDTO reservation1dto = new ReservationDTO();
        reservation1dto.setCar(car1dto);
        reservation1dto.setEmployee(employee1dto);

        
        EmployeeDTO employee2dto = new EmployeeDTO();
        employee2dto.setId(new Long(2));
        employee2dto.setName("Silvester Vlkoucho");

        CarDTO car2dto = new CarDTO();
        car2.setSpz("BA125EX");

        ReservationDTO reservation2dto = new ReservationDTO();
        reservation2dto.setCar(car2dto);
        reservation2dto.setEmployee(employee2dto);

        
        EmployeeDTO employee3dto = new EmployeeDTO();
        employee3dto.setId(new Long(3));
        employee3dto.setName("Margareta Svietislnkova");

        CarDTO car3dto = new CarDTO();
        car3.setSpz("BC234EX");

        ReservationDTO reservation3dto = new ReservationDTO();
        reservation3dto.setCar(car3dto);
        reservation3dto.setEmployee(employee3dto);   
        
        

        List<Reservation> allEntities = new ArrayList<Reservation>();
        allEntities.add(reservation1);
        allEntities.add(reservation2);
        allEntities.add(reservation3);

        List<ReservationDTO> allDTO = new ArrayList<ReservationDTO>();
        allDTO.add(reservation1dto);
        allDTO.add(reservation2dto);
        allDTO.add(reservation3dto);

        when(reservationDao.getAllReservations()).thenReturn(allEntities);
        List<ReservationDTO> returnedReservations = reservationService.getAllReservations();
        assertEquals(allDTO.size(), returnedReservations.size());
        for (int i = 0; i < returnedReservations.size(); i++) {
            assertEquals(allDTO.get(i), returnedReservations.get(i));
        }
        verify(reservationDao, times(1)).getAllReservations();
    }
    
    
    @Test
    public void removeReservation(){
        
    }
    
    
}

package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DAO.CarDAO;
import cz.muni.fi.pa165.vozovypark.DAO.ReservationDAO;
import cz.muni.fi.pa165.vozovypark.DTO.CarDTO;
import cz.muni.fi.pa165.vozovypark.DTO.EmployeeDTO;
import cz.muni.fi.pa165.vozovypark.DTO.ReservationDTO;
import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import cz.muni.fi.pa165.vozovypark.entities.Reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Eduard Krak
 */
@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private ReservationDAO reservationDao;
    private CarDAO carDao;
    private Mapper mapper;

    public void setCarDao(CarDAO carDao) {
        this.carDao = carDao;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    public void setReservationDao(ReservationDAO reservationDao) {
        this.reservationDao = reservationDao;
    }

    @PreAuthorize("hasRole('sysAdmin')")
    public ReservationDTO createReservation(ReservationDTO reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation name is not specified");
        }
        if (reservation.getEmployee() == null) {
            throw new IllegalArgumentException("Employee in reservation is not specified");
        }
        if (reservation.getCar() == null) {
            throw new IllegalArgumentException("Car in reservation is not specified");
        }
        Reservation res = mapper.map(reservation, Reservation.class);
        reservationDao.insert(res);

        return mapper.map(res, ReservationDTO.class);
    }

    @PreAuthorize("hasRole('sysAdmin')")
    public ReservationDTO updateReservation(ReservationDTO reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation name is not specified");
        }
        if (reservation.getId() == null) {
            throw new IllegalArgumentException("Reservation ID is not specified");
        }
        if (reservation.getEmployee() == null) {
            throw new IllegalArgumentException("Employee in reservation is not specified");
        }
        if (reservation.getCar() == null) {
            throw new IllegalArgumentException("Car in reservation is not specified");
        }
        Reservation res = mapper.map(reservation, Reservation.class);
        reservationDao.update(res);

        return mapper.map(res, ReservationDTO.class);
    }

    @PreAuthorize("hasRole('manager') or hasRole('sysAdmin')")
    public ReservationDTO getReservationById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is not specified");
        }
        Reservation reservationById = reservationDao.getReservationById(id);
        if (reservationById == null) {
            return null;
        }
        return mapper.map(reservationById, ReservationDTO.class);
    }

    @PreAuthorize("hasRole('manager') or hasRole('sysAdmin')")
    public List<ReservationDTO> getReservationsByEmployee(EmployeeDTO employee) {
        if (employee == null) {
            throw new IllegalArgumentException("ID is not specified");
        }
        List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
        for (Reservation res : reservationDao.getReservationByEmployee(mapper.map(employee, Employee.class))) {
            reservations.add(mapper.map(res, ReservationDTO.class));
        }
        return reservations;
    }

    @PreAuthorize("hasRole('manager') or hasRole('sysAdmin')")
    public List<ReservationDTO> getReservationsByCar(CarDTO car) {
        if (car == null) {
            throw new IllegalArgumentException("Car is not specified");
        }
        List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
        for (Reservation res : reservationDao.getReservationByCar(mapper.map(car, Car.class))) {
            reservations.add(mapper.map(res, ReservationDTO.class));
        }
        return reservations;
    }

    @PreAuthorize("hasRole('manager') or hasRole('sysAdmin')")
    public List<ReservationDTO> getReservationsByCarAndEmployee(CarDTO car, EmployeeDTO employee) {
        if (car == null) {
            throw new IllegalArgumentException("Car is not specified");
        }
        if (employee == null) {
            throw new IllegalArgumentException("Employee is not specified");
        }
        List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
        for (Reservation res : reservationDao.getReservationByCarAndEmployee(mapper.map(car, Car.class), mapper.map(employee, Employee.class))) {
            reservations.add(mapper.map(res, ReservationDTO.class));
        }
        return reservations;
    }

    @PreAuthorize("hasRole('manager') or hasRole('sysAdmin')")
    public List<ReservationDTO> getReservationsToConfirm() {

        List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
        for (Reservation res : reservationDao.getReservationsToConfirm()) {
            reservations.add(mapper.map(res, ReservationDTO.class));
        }
        return reservations;
    }

    @PreAuthorize("hasRole('manager') or hasRole('sysAdmin')")
    public List<ReservationDTO> getAcceptedReservations() {

        List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
        for (Reservation res : reservationDao.getAcceptedReservations()) {
            reservations.add(mapper.map(res, ReservationDTO.class));
        }
        return reservations;
    }

    public ReservationDTO rentCar(Long reservationId) {
        if (reservationId == null) {
            throw new IllegalArgumentException("ID is not specified");
        }
        Reservation res = reservationDao.getReservationById(reservationId);
        if (res == null) {
            throw new IllegalArgumentException("Reservation with this ID does not exist");
        }
        res.setStartDate(new Date());

        Car car = res.getCar();
        car.setAvailable(Boolean.FALSE);
        carDao.update(car);

        reservationDao.update(res);
        return mapper.map(res, ReservationDTO.class);
    }

    public ReservationDTO returnCar(Long reservationId) {
        if (reservationId == null) {
            throw new IllegalArgumentException("ID is not specified");
        }
        Reservation res = reservationDao.getReservationById(reservationId);
        if (res == null) {
            throw new IllegalArgumentException("Reservation with this ID does not exist");
        }
        res.setReturnDate(new Date());

        Car car = res.getCar();
        car.setAvailable(Boolean.TRUE);
        carDao.update(car);

        reservationDao.update(res);
        return mapper.map(res, ReservationDTO.class);
    }

    @PreAuthorize("hasRole('sysAdmin')")
    public void removeReservation(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Reservation ID is not specified");
        }
        Reservation reservation = reservationDao.getReservationById(id);
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation with this ID does not exist");
        }

        reservationDao.remove(reservation);
    }

    @PreAuthorize("hasRole('manager') or hasRole('sysAdmin')")
    public List<ReservationDTO> getAllReservations() {

        List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
        for (Reservation res : reservationDao.getAllReservations()) {
            reservations.add(mapper.map(res, ReservationDTO.class));
        }
        return reservations;
    }

    public ReservationDTO acceptReservation(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Reservation ID is not specified");
        }
        Reservation reservation = reservationDao.getReservationById(id);
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation with this ID does not exist");
        }
        reservation.setConfirmed(true);
        reservationDao.update(reservation);
        return mapper.map(reservation, ReservationDTO.class);
    }
}

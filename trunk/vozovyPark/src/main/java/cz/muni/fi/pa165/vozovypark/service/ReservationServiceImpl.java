/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DAO.CarDAO;
import cz.muni.fi.pa165.vozovypark.DAO.ReservationDAO;
import cz.muni.fi.pa165.vozovypark.DTO.CarDTO;
import cz.muni.fi.pa165.vozovypark.DTO.EmployeeDTO;
import cz.muni.fi.pa165.vozovypark.DTO.ReservationDTO;
import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import cz.muni.fi.pa165.vozovypark.entities.Reservation;
import cz.muni.fi.pa165.vozovypark.service.utils.Adapters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Eduard Krak
 */
public class ReservationServiceImpl implements ReservationService{

    private ReservationDAO reservationDao;
    private CarDAO carDao;

    public void setCarDao(CarDAO carDao) {
        this.carDao = carDao;
    }

    public void setReservationDao(ReservationDAO reservationDao) {
        this.reservationDao = reservationDao;
    }
    
    
    public ReservationDTO createReservation(ReservationDTO reservation) {
        if (reservation==null) {
            throw new IllegalArgumentException("Reservation name is not specified");
        }
        if (reservation.getEmployee() == null) {
            throw new IllegalArgumentException("Employee in reservation is not specified");
        }
        if (reservation.getCar() == null) {
            throw new IllegalArgumentException("Car in reservation is not specified");
        }
        Reservation res = Adapters.ReservationDtoToEntity(reservation);
        reservationDao.insert(res);
        
        return Adapters.ReservationEntityToDto(res);
    }

    public ReservationDTO updateReservation(ReservationDTO reservation) {
        if (reservation==null) {
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
        Reservation res = Adapters.ReservationDtoToEntity(reservation);
        reservationDao.update(res);
        
        return Adapters.ReservationEntityToDto(res);
    }

    public ReservationDTO getReservationById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is not specified");
        }
        return Adapters.ReservationEntityToDto(reservationDao.getReservationById(id));
    }

    public List<ReservationDTO> getReservationsByEmployee(EmployeeDTO employee) {
        if (employee == null) {
            throw new IllegalArgumentException("ID is not specified");
        }
        List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
        for (Reservation res : reservationDao.getReservationByEmployee(Adapters.EmployeeDtoToEntity(employee))){
            reservations.add(Adapters.ReservationEntityToDto(res));
        }
        return reservations;
    }

    public List<ReservationDTO> getReservationsByCar(CarDTO car) {
        if (car == null) {
            throw new IllegalArgumentException("Car is not specified");
        }
        List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
        for (Reservation res : reservationDao.getReservationByCar(Adapters.CarDtoToEntity(car))){
            reservations.add(Adapters.ReservationEntityToDto(res));
        }
        return reservations;
    }

    public List<ReservationDTO> getReservationsByCarAndEmployee(CarDTO car, EmployeeDTO employee) {
        if (car == null) {
            throw new IllegalArgumentException("Car is not specified");
        }
        if (employee == null) {
            throw new IllegalArgumentException("Employee is not specified");
        }
        List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
        for (Reservation res : reservationDao.getReservationByCarAndEmployee(Adapters.CarDtoToEntity(car), Adapters.EmployeeDtoToEntity(employee))){
            reservations.add(Adapters.ReservationEntityToDto(res));
        }
        return reservations;
    }

    public List<ReservationDTO> getReservationsToConfirm() {
        
        List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
        for (Reservation res : reservationDao.getReservationsToConfirm()){
            reservations.add(Adapters.ReservationEntityToDto(res));
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
        return Adapters.ReservationEntityToDto(res);
    }

    public ReservationDTO returnCar(Long reservationId) {
        if (reservationId== null) {
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
        return Adapters.ReservationEntityToDto(res);
    }

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
    
    public List<ReservationDTO> getAllReservations() {
        
        List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
        for (Reservation res : reservationDao.getAllReservations()){
            reservations.add(Adapters.ReservationEntityToDto(res));
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
        return Adapters.ReservationEntityToDto(reservation);
    }
}

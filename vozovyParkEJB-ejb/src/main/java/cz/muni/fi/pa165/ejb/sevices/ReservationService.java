/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.ejb.sevices;

import cz.muni.fi.pa165.ejb.DTO.CarDTO;
import cz.muni.fi.pa165.ejb.DTO.EmployeeDTO;
import cz.muni.fi.pa165.ejb.DTO.ReservationDTO;
import java.util.List;

/**
 *
 * @author acer
 */
public interface ReservationService {
    
    /**
     * Creates given reservation in database
     * @param reservation Reservation to store
     * @return Stored reservation
     */
    ReservationDTO createReservation(ReservationDTO reservation);
    
    /**
     * Updates given reservation in database
     * @param reservation Reservation to update
     * @return Updated reservation
     */
    ReservationDTO updateReservation(ReservationDTO reservation);
    
    /**
     * Returns reservation from database with given id
     * @param id ID of wanted reservation
     * @return Reservation from database with given id 
     */
    ReservationDTO getReservationById(Long id);
    
    /**
     * Returns reservation connected with given employee
     * @param employee Employee to find reservations connected with him
     * @return Reservations connected with given employee
     */
    List<ReservationDTO> getReservationsByEmployee(EmployeeDTO employee);
    
    /**
     * Returns reservations connected with given car
     * @param car Car to find reservations connected with it
     * @return Reservations connected with given car
     */
    List<ReservationDTO> getReservationsByCar(CarDTO car);
    
    /**
     * Returns reservations connected with given car and employee
     * @param car Car to find reservations connected with it
     * @param  employee Employee to find reservations connected with it
     * @return Reservations connected with given car and employee
     */
    List<ReservationDTO> getReservationsByCarAndEmployee(CarDTO car, EmployeeDTO employee);
    
    /**
     * Returns reservations that are not confirmed
     * @return Not confirmed reservations
     */
    List<ReservationDTO> getReservationsToConfirm();
    
    /**
     * Rent the car to employee and save current date to reservation
     * @param reservationId Reservation to start
     * @return Reservation - informations about rent
     */
    ReservationDTO rentCar(Long reservationId);
   
    /**
     * Car is returned and current date is saved to reservation
     * @param reservationId Reservation to end
     * @return Reservation - informations about rent
     */
    ReservationDTO returnCar(Long reservationId);
    
    /**
     * Returns all reservations from database
     * @return all reservations from database
     */
    List<ReservationDTO> getAllReservations();
    
    /**
     * Removes reservation from database
     * @param id ID of reservation
     */
    void removeReservation(Long id);
    
    /**
     * Accepts unconfirmed reservation
     * @param id ID of reservation
     * @return Accepted reservation
     */
    ReservationDTO acceptReservation(Long id);    
}

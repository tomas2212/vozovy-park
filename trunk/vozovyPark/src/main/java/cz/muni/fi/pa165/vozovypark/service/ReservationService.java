package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DTO.CarDTO;
import cz.muni.fi.pa165.vozovypark.DTO.EmployeeDTO;
import cz.muni.fi.pa165.vozovypark.DTO.ReservationDTO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author andrej
 */
public interface ReservationService {
    
    ReservationDTO createReservation(Long carId, Long employeeId, Date dateFrom, Date dateTo);
    
    ReservationDTO updateReservation(ReservationDTO reservation);
    
    ReservationDTO getReservationById(Long id);
    
    List<ReservationDTO> getReservationsByEmployee(EmployeeDTO employee);
    
    List<ReservationDTO> getReservationsByCar(CarDTO car);
    
    List<ReservationDTO> getReservationsByCarAndEmployee(CarDTO car, EmployeeDTO employee);
    
    List<ReservationDTO> getReservationsToConfirm();
    
    ReservationDTO rentCar(Long reserVationId);
   
    ReservationDTO returnCar(Long reservationId);
    
    void removeReservation(Long id);
    
     
    
}

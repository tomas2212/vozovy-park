package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DTO.CarDTO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import java.util.List;

/**
 *
 * @author Tomas Svrcek
 */
public interface CarService {
    
    CarDTO createCar(CarDTO car);
    
    CarDTO updateCar(CarDTO car);
    
    CarDTO setCarAvailable(Long id, Boolean available);
    
    void removeCar(Long id);
    
    CarDTO getCarById(Long id);
    
    List<CarDTO> getAllCars();
    
    List<CarDTO> getCarsByCompanyLevel(CompanyLevelDTO companyLevel);
            
}

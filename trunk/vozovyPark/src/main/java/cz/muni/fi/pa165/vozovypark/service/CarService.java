/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DTO.CarDTO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import java.util.List;

/**
 *
 * @author andrej
 */
public interface CarService {
    
    CarDTO createCar(CarDTO car);
    
    CarDTO updateCar(CarDTO car);
    
    CarDTO setCarAvailable(Long id, Boolean available);
    
    CarDTO removeCar(Long id);
    
    CarDTO getCarById(Long id);
    
    List<CarDTO> getAllCars();
    
    List<CarDTO> getCarsByCompanyLevel(CompanyLevelDTO companyLevel);
            
}

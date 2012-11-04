package cz.muni.fi.pa165.vozovypark.service.utils;

import cz.muni.fi.pa165.vozovypark.DTO.CarDTO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.DTO.EmployeeDTO;
import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.entities.Employee;

/**
 *
 * @author Andrej Bauer
 */
public class Adapters {
    public static EmployeeDTO EmployeeEntityToDto(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setPosition(employee.getPosition());
        dto.setApprove(employee.getApprove());
        dto.setAddress(employee.getAddress());

        return dto;
    }
    
    public static Employee EmployeeDtoToEntity(EmployeeDTO employee) {
        Employee entity = new Employee();
        entity.setId(employee.getId());
        entity.setName(employee.getName());
        entity.setPosition(employee.getPosition());
        entity.setApprove(employee.getApprove());
        entity.setAddress(employee.getAddress());

        return entity;
    }
    
    public static CarDTO CarEntityToDto(Car car) {
        CarDTO dto = new CarDTO();
        dto.setId(car.getId());
        dto.setAvailable(car.getAvailable());
        dto.setBrand(car.getBrand());
        dto.setCompanyLevel(CompanyLevelEntityToDto(car.getCompanyLevel()));
        dto.setCreationYear(car.getCreationYear());
        dto.setModel(car.getModel());
        dto.setSpz(car.getSpz());

        return dto;
    }

    public static Car CarDtoToEntity(CarDTO car) {
        Car entity = new Car();
        entity.setId(car.getId());
        entity.setSpz(car.getSpz());
        entity.setBrand(car.getBrand());
        entity.setCompanyLevel(CompanyLevelDtoToEntity(car.getCompanyLevel()));
        entity.setCreationYear(car.getCreationYear());
        entity.setModel(car.getModel());

        return entity;
    }
    
    public static CompanyLevelDTO CompanyLevelEntityToDto(CompanyLevel companyLevel){
        CompanyLevelDTO dto = new CompanyLevelDTO();
        dto.setId(companyLevel.getId());
        dto.setLevelValue(companyLevel.getLevelValue());
        dto.setName(companyLevel.getName());
        return dto;
    }
    
    public static CompanyLevel CompanyLevelDtoToEntity(CompanyLevelDTO companyLevel){
        CompanyLevel entity = new CompanyLevel();
        entity.setId(companyLevel.getId());
        entity.setLevelValue(companyLevel.getLevelValue());
        entity.setName(companyLevel.getName());
        return entity;     
    }
}

package cz.muni.fi.pa165.vozovypark.service.utils;

import cz.muni.fi.pa165.vozovypark.DTO.CarDTO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.DTO.EmployeeDTO;
import cz.muni.fi.pa165.vozovypark.DTO.ReservationDTO;
import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import cz.muni.fi.pa165.vozovypark.entities.Reservation;

/**
 *
 * @author Andrej Bauer
 */
public class Adapters {
    public static EmployeeDTO EmployeeEntityToDto(Employee employee) {
        if(employee == null){
            return null;
        }
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setPosition(employee.getPosition());
        dto.setApprove(employee.getApprove());
        dto.setAddress(employee.getAddress());
        dto.setCompanyLevel(CompanyLevelEntityToDto(employee.getCompanyLevel()));
        return dto;
    }
    
    public static Employee EmployeeDtoToEntity(EmployeeDTO employee) {
        if(employee == null){
            return null;
        }
        Employee entity = new Employee();
        entity.setId(employee.getId());
        entity.setName(employee.getName());
        entity.setPosition(employee.getPosition());
        entity.setApprove(employee.getApprove());
        entity.setAddress(employee.getAddress());
        entity.setCompanyLevel(CompanyLevelDtoToEntity(employee.getCompanyLevel()));

        return entity;
    }
    
    public static CarDTO CarEntityToDto(Car car) {
        if(car == null){
            return null;
        }
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
        if(car == null){
            return null;
        }
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
        if(companyLevel == null){
            return null;
        }
        CompanyLevelDTO dto = new CompanyLevelDTO();
        dto.setId(companyLevel.getId());
        dto.setLevelValue(companyLevel.getLevelValue());
        dto.setName(companyLevel.getName());
        return dto;
    }
    
    public static CompanyLevel CompanyLevelDtoToEntity(CompanyLevelDTO companyLevel){
        if(companyLevel == null){
            return null;
        }
        CompanyLevel entity = new CompanyLevel();
        entity.setId(companyLevel.getId());
        entity.setLevelValue(companyLevel.getLevelValue());
        entity.setName(companyLevel.getName());
        return entity;     
    }
    
    public static Reservation ReservationDtoToEntity(ReservationDTO reservation) {
        if(reservation == null){
            return null;
        }
        Reservation resToReturn = new Reservation();
        resToReturn.setCar(CarDtoToEntity(reservation.getCar()));
        resToReturn.setDateFrom(reservation.getDateFrom());
        resToReturn.setDateTo(reservation.getDateTo());
        resToReturn.setEmployee(EmployeeDtoToEntity(reservation.getEmployee()));
        resToReturn.setReturnDate(reservation.getReturnDate());
        resToReturn.setStartDate(reservation.getStartDate());
        return resToReturn;
    }
    
    public static ReservationDTO ReservationEntityToDto(Reservation reservation) {
        if(reservation == null){
            return null;
        }
        ReservationDTO resToReturn = new ReservationDTO();
        resToReturn.setCar(CarEntityToDto(reservation.getCar()));
        resToReturn.setDateFrom(reservation.getDateFrom());
        resToReturn.setDateTo(reservation.getDateTo());
        resToReturn.setEmployee(EmployeeEntityToDto(reservation.getEmployee()));
        resToReturn.setReturnDate(reservation.getReturnDate());
        resToReturn.setStartDate(reservation.getStartDate());
        return resToReturn;
    }
}

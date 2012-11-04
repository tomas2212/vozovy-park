package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DAO.CarDAO;
import cz.muni.fi.pa165.vozovypark.DTO.CarDTO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomas Svrcek
 */
public class CarServiceImpl implements CarService {

    CarDAO carDao;

    public void setCarDao(CarDAO carDao) {
        this.carDao = carDao;
    }

    public CarDTO createCar(CarDTO carName) {
        if (carName == null) {
            throw new IllegalArgumentException("Car is not specified");
        }
        Car car = new Car();
        car.setId(carName.getId());
        car.setAvailable(carName.getAvailable());
        car.setBrand(carName.getBrand());
        car.setModel(carName.getModel());
        car.setSpz(carName.getSpz());
        car.setCreationYear(carName.getCreationYear());
//        car.setCompanyLevel(carName.getCompanyLevel());

        carDao.insert(car);

        return EntityToDtoAdapter(car);
    }

    public CarDTO updateCar(CarDTO car) {
        if (car == null) {
            throw new IllegalArgumentException("Car name is not specified");
        }
        if (car.getId() == null) {
            throw new IllegalArgumentException("Car ID is not specified");
        }
        Car entity = DtoToEntityAdapter(car);
        carDao.update(entity);
        return EntityToDtoAdapter(entity);
    }

    public CarDTO setCarAvailable(Long id, Boolean available) {
        if (id == null) {
            throw new IllegalArgumentException("ID is not specified");
        }
        if (available == null) {
            throw new IllegalArgumentException("Availability is not specified");
        }   
        Car car = carDao.getCarById(id);
        car.setAvailable(available);
        carDao.update(car);
        return EntityToDtoAdapter(car);
    }

    public void removeCar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is not specified");
        }
        Car car = carDao.getCarById(id);
        if (car == null) {
            throw new IllegalArgumentException("Car with this ID does not exist");
        }
        carDao.remove(car);
    }

    public CarDTO getCarById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is not specified");
        }
        return EntityToDtoAdapter(carDao.getCarById(id));
    }

    public List<CarDTO> getAllCars() {
        List<CarDTO> cars = new ArrayList<CarDTO>();
        List<Car> allCars = carDao.getAllCars();
        for(Car c : allCars){
            cars.add(EntityToDtoAdapter(c));
        }
        return cars;
    }

    public List<CarDTO> getCarsByCompanyLevel(CompanyLevelDTO companyLevel) {
        if (companyLevel == null) {
            throw new IllegalArgumentException("CompanyLevel is not specified");
        }        
        List<CarDTO> cars = new ArrayList<CarDTO>();
 //       CompanyLevel cl = DtoToEntityAdapter(companyLevel);
//        List<Car> clCars = carDao.getAllCarsWithHigherLevel(cl);
 //       for(Car c : clCars){
 //           cars.add(EntityToDtoAdapter(c));
 //       }
        return cars;
    }

    private CarDTO EntityToDtoAdapter(Car car) {
        CarDTO dto = new CarDTO();
        dto.setId(car.getId());
        dto.setAvailable(car.getAvailable());
        dto.setBrand(car.getBrand());
//        dto.setCompanyLevel(car.getCompanyLevel());
        dto.setCreationYear(car.getCreationYear());
        dto.setModel(car.getModel());
        dto.setSpz(car.getSpz());

        return dto;
    }

    private Car DtoToEntityAdapter(CarDTO car) {
        Car entity = new Car();
        entity.setId(car.getId());
        entity.setSpz(car.getSpz());
        entity.setBrand(car.getBrand());
        //       entity.setCompanyLevel(car.getCompanyLevel());
        entity.setCreationYear(car.getCreationYear());
        entity.setModel(car.getModel());

        return entity;
    }
}

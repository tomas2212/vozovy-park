package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DAO.CarDAO;
import cz.muni.fi.pa165.vozovypark.DTO.CarDTO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.service.utils.Adapters;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomas Svrcek
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {

    CarDAO carDao;

    public void setCarDao(CarDAO carDao) {
        this.carDao = carDao;
    }

    public CarDTO createCar(CarDTO car) {
        if (car == null) {
            throw new IllegalArgumentException("Car is not specified");
        }
        carDao.insert(Adapters.CarDtoToEntity(car));

        return car;
    }

    public CarDTO updateCar(CarDTO car) {
        if (car == null) {
            throw new IllegalArgumentException("Car name is not specified");
        }
        
        Car entity = Adapters.CarDtoToEntity(car);
        
        if (entity.getId() == null) {
            throw new IllegalArgumentException("Car ID is not specified");
        }
        carDao.update(entity);
        return Adapters.CarEntityToDto(entity);
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

        return Adapters.CarEntityToDto(car);
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
        return Adapters.CarEntityToDto(carDao.getCarById(id));
    }

    public List<CarDTO> getAllCars() {
        List<CarDTO> cars = new ArrayList<CarDTO>();
        List<Car> allCars = carDao.getAllCars();
        for (Car c : allCars) {
            cars.add(Adapters.CarEntityToDto(c));
        }
        return cars;
    }

    public List<CarDTO> getCarsByCompanyLevel(CompanyLevelDTO companyLevel) {
        if (companyLevel == null) {
            throw new IllegalArgumentException("CompanyLevel is not specified");
        }
        List<CarDTO> cars = new ArrayList<CarDTO>();
        CompanyLevel cl = Adapters.CompanyLevelDtoToEntity(companyLevel);
        List<Car> clCars = carDao.getAllCarsWithHigherLevel(cl);
        for (Car c : clCars) {
            cars.add(Adapters.CarEntityToDto(c));
        }
        return cars;
    }
}
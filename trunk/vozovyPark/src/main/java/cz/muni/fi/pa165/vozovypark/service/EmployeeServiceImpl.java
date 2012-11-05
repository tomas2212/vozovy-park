package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DAO.EmployeeDAO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.DTO.EmployeeDTO;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import cz.muni.fi.pa165.vozovypark.service.utils.Adapters;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukas Maticky
 */
@Repository
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public EmployeeDTO createEmployee(EmployeeDTO employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee is not specified");
        }
        if (employee.getName() == null) {
            throw new IllegalArgumentException("Employee name is not specified");
        }
        Employee ead = Adapters.EmployeeDtoToEntity(employee);
        employeeDAO.insert(ead);


        return Adapters.EmployeeEntityToDto(ead);
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee name is not specified");
        }
        if (employee.getId() == null) {
            throw new IllegalArgumentException("Employee ID is not specified");
        }
        Employee entity = Adapters.EmployeeDtoToEntity(employee);
        employeeDAO.update(entity);
        return Adapters.EmployeeEntityToDto(entity);

    }

    public void removeEmployee(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is not specified");
        }
        Employee employee = employeeDAO.getEmployeeById(id);
        if (employee == null) {
            throw new IllegalArgumentException("Employee with this ID does not exist");
        }
        employeeDAO.remove(employee);
    }

    public EmployeeDTO getEmployeeById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is not specified");
        }
        return Adapters.EmployeeEntityToDto(employeeDAO.getEmployeeById(id));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employee = new ArrayList<EmployeeDTO>();
        List<Employee> allEmployee = employeeDAO.getAllEmployee();
        for (Employee el : allEmployee) {
            employee.add(Adapters.EmployeeEntityToDto(el));
        }
        return employee;
    }

    public List<EmployeeDTO> getEmployeesByCompanyLevel(CompanyLevelDTO companyLevel) {
        if (companyLevel == null) {
            throw new IllegalArgumentException("CompanyLevel is not specified");
        }
        List<EmployeeDTO> employee = new ArrayList<EmployeeDTO>();
        CompanyLevel cl = Adapters.CompanyLevelDtoToEntity(companyLevel);
        List<Employee> clEmployees = employeeDAO.getAllEmployeeWithHigherLevel(cl);
        for (Employee e : clEmployees) {
            employee.add(Adapters.EmployeeEntityToDto(e));
        }

        return employee;
    }
}

package cz.muni.fi.pa165.ejb.sevices;

import cz.muni.fi.pa165.ejb.DTO.EmployeeDTO;
import cz.muni.fi.pa165.ejb.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.ejb.dao.EmployeeDAO;
import cz.muni.fi.pa165.ejb.entities.Employee;
import cz.muni.fi.pa165.ejb.entities.CompanyLevel;
import cz.muni.fi.pa165.ejb.services.utils.Adapters;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Lukas Maticky
 */
@Stateless
@Local(value = EmployeeService.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class EmployeeServiceImpl implements EmployeeService {

    @EJB
    EmployeeDAO employeeDAO;

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

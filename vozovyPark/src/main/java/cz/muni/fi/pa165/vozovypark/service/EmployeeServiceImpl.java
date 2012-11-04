package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DAO.EmployeeDAO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.DTO.EmployeeDTO;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lukas Maticky
 */
@Repository
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeName) {
        if (employeeName == null) {
            throw new IllegalArgumentException("Employee name is not specified");
        }

        Employee employee = new Employee();
        employee.setAddress(employeeName.getAddress());
        employee.setId(employeeName.getId());
        employee.setName(employeeName.getName());
        employee.setPosition(employeeName.getPosition());
        employee.setApprove(employeeName.getApprove());
        employeeDAO.insert(employee);

        return EntityToDtoAdapter(employee);
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee name is not specified");
        }
        if (employee.getId() == null) {
            throw new IllegalArgumentException("Employee ID is not specified");
        }
        Employee entity = DtoToEntityAdapter(employee);
        employeeDAO.update(entity);
        return EntityToDtoAdapter(entity);

    }

    public EmployeeDTO getEmployeeById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is not specified");
        }
        return EntityToDtoAdapter(employeeDAO.getEmployeeById(id));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employee = new ArrayList<EmployeeDTO>();
        List<Employee> allEmployee = employeeDAO.getAllEmployee();
        for (Employee el : allEmployee) {
            employee.add(EntityToDtoAdapter(el));
        }
        return employee;
    }

    public List<EmployeeDTO> getEmployeesByCompanyLevel(CompanyLevelDTO companyLevel) {
        if (companyLevel == null) {
            throw new IllegalArgumentException("CompanyLevel is not specified");
        }
        List<EmployeeDTO> employee = new ArrayList<EmployeeDTO>();

        return employee;
    }

    private EmployeeDTO EntityToDtoAdapter(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setPosition(employee.getPosition());
        dto.setApprove(employee.getApprove());
        dto.setAddress(employee.getAddress());

        return dto;
    }

    private Employee DtoToEntityAdapter(EmployeeDTO employee) {
        Employee entity = new Employee();
        entity.setId(employee.getId());
        entity.setName(employee.getName());
        entity.setPosition(employee.getPosition());
        entity.setApprove(employee.getApprove());
        entity.setAddress(employee.getAddress());

        return entity;

    }
}

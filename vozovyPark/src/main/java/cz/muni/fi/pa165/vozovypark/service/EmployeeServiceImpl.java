package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DAO.EmployeeDAO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.DTO.EmployeeDTO;
import java.util.List;

/**
 *
 * @author andrej
 */
public class EmployeeServiceImpl implements EmployeeService {
    
    private EmployeeDAO employeeDAO;

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
    
    public EmployeeDTO createEmployee(EmployeeDTO employee) {
        return null;
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employee) {
        return null;
    }

    public EmployeeDTO getEmployeeById(Long id) {
       return null;
    }

    public List<EmployeeDTO> getAllEmployees() {
       return null;
    }

    public List<EmployeeDTO> getEmployeesByCompanyLevel(CompanyLevelDTO companyLevel) {
       return null;
    }
    
}

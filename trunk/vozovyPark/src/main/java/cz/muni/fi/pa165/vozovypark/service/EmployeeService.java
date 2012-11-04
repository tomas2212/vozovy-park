package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.DTO.EmployeeDTO;
import java.util.List;


/**
 *
 * @author andrej
 */
public interface EmployeeService {
    
    EmployeeDTO createEmployee(EmployeeDTO employee);
    
    EmployeeDTO updateEmployee(EmployeeDTO employee);
    
    EmployeeDTO getEmployeeById(Long id);
    
    List<EmployeeDTO> getAllEmployees();
    
    List<EmployeeDTO> getEmployeesByCompanyLevel(CompanyLevelDTO companyLevel);
    
    
    
}

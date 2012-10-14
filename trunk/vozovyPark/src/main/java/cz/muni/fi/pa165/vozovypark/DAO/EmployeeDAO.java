/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import java.util.List;

/**
 *
 * @author Tomas
 */
public interface EmployeeDAO {

    /**
    
     */
    public Employee getEmployeeById(Long id);

    /**
    
     */
    public Employee getEmployeeByName(String name);

    /**
    
     */
    public Employee getEmployeeByAddress(String address);

    /**
    
     */
    public void insert(Employee employee);

    /**
    
     */
    public void remove(Employee employee);

    /**
    
     */
    public void update(Employee employee);

    /**
    
     */
    public List<Employee> getAllEmployee();

    public List<Employee> getAllEmployeeWithHigherLevel(CompanyLevel companyLevel);
}

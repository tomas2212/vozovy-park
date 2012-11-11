/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.ejb.tests;

import cz.muni.fi.pa165.ejb.DTO.CarDTO;
import cz.muni.fi.pa165.ejb.DTO.EmployeeDTO;
import cz.muni.fi.pa165.ejb.dao.EmployeeDAO;
import cz.muni.fi.pa165.ejb.entities.Car;
import cz.muni.fi.pa165.ejb.entities.Employee;
import cz.muni.fi.pa165.ejb.sevices.EmployeeService;
import cz.muni.fi.pa165.ejb.sevices.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 *
 * @author Lukas Maticky
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeTest {
     @Mock
    EmployeeDAO employeeDAO;
    
    @InjectMocks
    EmployeeService employeeService = new EmployeeServiceImpl();
    
    
    @Test
    public void testCreateEmployee() {

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Lukas");

        
        employeeService.createEmployee(employeeDTO);
        verify(employeeDAO, times(1)).insert(any(Employee.class));

        try {
            employeeService.createEmployee(null);
            fail("accepted null value");
        } catch (IllegalArgumentException e) {
        }

        //testing if employeeDAO was called before throwing exception
        verify(employeeDAO, never()).insert(null);
    }
    
    @Test
    public void testUpdateEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Lukas");
        employeeDTO.setId(new Long(1));

        EmployeeDTO noIdDto = new EmployeeDTO();
        employeeDTO.setPosition("Principal");

        employeeService.updateEmployee(employeeDTO);
        verify(employeeDAO, times(1)).update(any(Employee.class));

        try {
            employeeService.updateEmployee(null);
            fail("Implementation accepted null value");
        } catch (IllegalArgumentException e) {
        }

        //testing if employeeDAO was called before throwing exception
        verify(employeeDAO, never()).update(null);

        try {
            employeeService.updateEmployee(noIdDto);
            fail("Implementation accepted no id");
        } catch (IllegalArgumentException e) {
        }
    }
    
    @Test
    public void testRemoveEmployee(){
       Employee employee = new Employee();
        employee.setId(new Long(1));
        employee.setName("Martin");

        when(employeeDAO.getEmployeeById(new Long(1))).thenReturn(employee);
         
        employeeService.removeEmployee(new Long(1));
        verify(employeeDAO, times(1)).remove(any(Employee.class));
        
        try{
            employeeService.removeEmployee(null);
            fail("accepted null id");
        }
        catch(IllegalArgumentException e) {}
    }
}

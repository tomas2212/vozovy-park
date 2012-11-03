/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.ServiceTests;

import cz.muni.fi.pa165.vozovypark.DAO.EmployeeDAO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.DTO.EmployeeDTO;
import cz.muni.fi.pa165.vozovypark.service.EmployeeServiceImpl;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author andrej
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
    
    @Mock private EmployeeDAO employeeDao;
     @InjectMocks private EmployeeServiceImpl employeeService;

    @Test
    public void testCreateEmployee() {
        
    }

    @Test
    public void testUpdateEmployee() {
    }

    @Test
    public void testGetEmployeeById() {
    }

    @Test
    public void testGetAllEmployees() {
    }

    @Test
    public void testGetEmployeesByCompanyLevel() {
    }

    
}

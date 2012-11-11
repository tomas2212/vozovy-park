/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.ejb.tests;

import cz.muni.fi.pa165.ejb.dao.CompanyLevelDAO;
import cz.muni.fi.pa165.ejb.dao.CompanyLevelDAOImpl;
import cz.muni.fi.pa165.ejb.entities.CompanyLevel;
import cz.muni.fi.pa165.ejb.sevices.CompanyLevelService;
import cz.muni.fi.pa165.ejb.sevices.CompanyLevelServiceImpl;
import javax.persistence.EntityManager;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author andrej
 */
@RunWith(MockitoJUnitRunner.class)
public class CompanyLevelTest {
    @Mock
    CompanyLevelDAO companyLevelDao;
    
    @InjectMocks
    CompanyLevelService companyLevelService = new CompanyLevelServiceImpl();
    
    
    @Test
    public void insertTest(){
        
        companyLevelService.createCompanyLevel("first level");
        verify(companyLevelDao, times(1)).insert(any(CompanyLevel.class));

        try {
            companyLevelService.createCompanyLevel(null);
            fail("accepted null value");
        } catch (IllegalArgumentException e) {
        }

        //testing if companyLevelDao was called before throwing exception
        verify(companyLevelDao, never()).insert(null);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.ServiceTests;

import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.DTO.EmployeeDTO;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.service.CompanyLevelService;
import cz.muni.fi.pa165.vozovypark.service.CompanyServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Lukas Maticky
 */

public class CompanyServiceTest extends AbstractServiceTest{
    
    
    @Autowired
    private CompanyLevelDAO companyLevelDao;
   
    @Autowired
    private CompanyLevelService companyLevelService;
    
    @Before
    public void setUp(){
        reset(companyLevelDao);
    }

    @Test
    public void testCreateCompanyLevel() {
       


        companyLevelService.createCompanyLevel("first level");
        verify(companyLevelDao, times(1)).insert(any(CompanyLevel.class));

        try {
            companyLevelService.createCompanyLevel(null);
            fail("accepted null value");
        } catch (IllegalArgumentException e) {
        }

        //testing if companyLevelDao was called before throwing exception
        verify(companyLevelDao, never()).insert(null);

        /*try {
            companyLevelService.createCompanyLevel(noNameDto);
            fail("accepted no name");
        } catch (IllegalArgumentException e) {
        }*/

    }
    //@Test
    public void testUpdateCompanyLevel() {
        CompanyLevelDTO companyLevelDto = new CompanyLevelDTO();
        companyLevelDto.setName("first level");
        companyLevelDto.setId(new Long(1));

        CompanyLevelDTO noIdDto = new CompanyLevelDTO();
        companyLevelDto.setName("second level");

        companyLevelService.updateCompanyLevel(companyLevelDto);
        verify(companyLevelDao, times(1)).update(any(CompanyLevel.class));

        try {
            companyLevelService.createCompanyLevel(null);
            fail("Implementation accepted null value");
        } catch (IllegalArgumentException e) {
        }

        //testing if companyLevelDao was called before throwing exception
        verify(companyLevelDao, never()).update(null);

       
    }
    
    @Test
    public void testCompanyLevelById() {
        CompanyLevel companyLevel = new CompanyLevel();
        companyLevel.setId(new Long(1));
        companyLevel.setName("first level");

        CompanyLevelDTO dto = new CompanyLevelDTO();
        dto.setId(new Long(1));
        dto.setName("first level");

        when(companyLevelDao.getCompanyLevelById(new Long(1))).thenReturn(companyLevel);
        CompanyLevelDTO companyLevelById = companyLevelService.getCompanyLevelById(new Long(1));
        assertEquals( dto, companyLevelById);
        
        verify(companyLevelDao, times(1)).getCompanyLevelById(eq(new Long(1)));

        try {
            companyLevelService.getCompanyLevelById(null);
            fail("accepted null id");

        } catch (IllegalArgumentException e) {
        }

        when(companyLevelDao.getCompanyLevelById(eq(new Long(2)))).thenReturn(null);
        assertNull(companyLevelService.getCompanyLevelById(new Long(2)));
    }
    
     @Test
    public void testgetAllCompanyLeves() {
        CompanyLevel companyLevel1 = new CompanyLevel();
        companyLevel1.setId(new Long(1));
        companyLevel1.setName("first level");

        CompanyLevel companyLevel2 = new CompanyLevel();
        companyLevel2.setId(new Long(2));
        companyLevel2.setName("second level");

        CompanyLevel companyLevel3 = new CompanyLevel();
        companyLevel3.setId(new Long(3));
        companyLevel3.setName("third level");

        CompanyLevelDTO companyLevel1dto = new CompanyLevelDTO();
        companyLevel1dto.setId(new Long(1));
        companyLevel1dto.setName("first level");

        CompanyLevelDTO companyLevel2dto = new CompanyLevelDTO();
        companyLevel2dto.setId(new Long(2));
        companyLevel2dto.setName("second level");

        CompanyLevelDTO companyLevel3dto = new CompanyLevelDTO();
        companyLevel3dto.setId(new Long(3));
        companyLevel3dto.setName("third level");
        
        List<CompanyLevel> allEntities = new ArrayList<CompanyLevel>();
        allEntities.add(companyLevel1);
        allEntities.add(companyLevel2);
        allEntities.add(companyLevel3);
        
        List<CompanyLevelDTO> allDTO = new ArrayList<CompanyLevelDTO>();
        allDTO.add(companyLevel1dto);
        allDTO.add(companyLevel2dto);
        allDTO.add(companyLevel3dto);
        
        when(companyLevelDao.getAllCompanyLevels()).thenReturn(allEntities);
        List<CompanyLevelDTO> returnedCompanyLevels = companyLevelService.getAllCompanyLevels();
        assertEquals(allDTO.size(), returnedCompanyLevels.size());
        for(int i = 0; i < returnedCompanyLevels.size(); i++){
            assertEquals(allDTO.get(i), returnedCompanyLevels.get(i));
        }
        verify(companyLevelDao, times(1)).getAllCompanyLevels();
        
    }
     
          
     @Test
    public void testRemooveCompanyLevel() {
         CompanyLevel companyLevel = new CompanyLevel();
        companyLevel.setId(new Long(1));
        companyLevel.setName("first level");

        when(companyLevelDao.getCompanyLevelById(new Long(1))).thenReturn(companyLevel);
         
        companyLevelService.removeCompanyLevel(new Long(1));
        verify(companyLevelDao, times(1)).remove(any(CompanyLevel.class));
        
        try{
            companyLevelService.removeCompanyLevel(null);
            fail("accepted null id");
        }
        catch(IllegalArgumentException e) {}
     }
}

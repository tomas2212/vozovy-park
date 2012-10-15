/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.DAOTests;

import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAO;
import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAOImpl;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomas Svrcek
 */
public class CompanyLevelDAOTest {
    
    private EntityManagerFactory emf;
    
    public CompanyLevelDAOTest() {
    }
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("TestPU");
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void insertTest(){
        CompanyLevel cl = new CompanyLevel();
        cl.setLevelValue(1);
        CompanyLevelDAO cldao = new CompanyLevelDAOImpl(emf);
        cldao.insert(cl);
        CompanyLevel cl2 = cldao.getCompanyLevelById(cl.getId());
        assertEquals(cl,cl2);
    }

}

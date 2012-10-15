/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.DAOTests;

import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAO;
import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAOImpl;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import java.sql.Connection;
import java.sql.DriverManager;
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
    Connection connection;
    @Before
    public void setUp() {
        
        try {
            
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:unit-testing-jpa", "sa", "");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Exception during HSQL database startup.");
        }
        try {
            
            emf = Persistence.createEntityManagerFactory("testPU");
            
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Exception during JPA EntityManager instanciation.");
        }
    }
    
    @After
    public void tearDown() {
        if (emf != null) {
            emf.close();
        }
        
        try {
            connection.createStatement().execute("SHUTDOWN");
        } catch (Exception ex) {
            System.err.println("Shutdown failed");
        }
        
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

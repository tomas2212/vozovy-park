package cz.muni.fi.pa165.vozovypark.DAOTests;

import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAO;
import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAOImpl;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lukas Maticky
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
    public void insertTest() {
        CompanyLevel cl = new CompanyLevel();
        cl.setLevelValue(1);

        CompanyLevelDAO cldao = new CompanyLevelDAOImpl(emf);
        cldao.insert(cl);

        CompanyLevel cl2 = cldao.getCompanyLevelById(cl.getId());

        assertEquals(cl, cl2);
    }

    @Test
    public void updateTest() {
        CompanyLevel cl1 = new CompanyLevel();
        cl1.setLevelValue(1);

        CompanyLevelDAO clDao = new CompanyLevelDAOImpl(emf);
        clDao.insert(cl1);

        CompanyLevel cl2 = clDao.getCompanyLevelById(cl1.getId());
        cl2.setLevelValue(2);
        clDao.update(cl2);

        CompanyLevel cl3 = clDao.getCompanyLevelById(cl1.getId());

        assertEquals(cl3.getLevelValue(), cl2.getLevelValue());
    }

    @Test
    public void deleteTest() {
        CompanyLevel cl1 = new CompanyLevel();
        cl1.setLevelValue(1);

        CompanyLevelDAO clDao = new CompanyLevelDAOImpl(emf);
        clDao.insert(cl1);

        CompanyLevel cl2 = clDao.getCompanyLevelById(cl1.getId());
        clDao.remove(cl2);

        assertNull(clDao.getCompanyLevelById(cl1.getId()));
    }

    @Test
    public void getCompanyLevelByIdTest() {
        CompanyLevel cl1 = new CompanyLevel();
        cl1.setLevelValue(1);

        CompanyLevelDAO clDao = new CompanyLevelDAOImpl(emf);
        clDao.insert(cl1);

        CompanyLevel cl2 = clDao.getCompanyLevelById(cl1.getId());

        assertEquals(cl1, cl2);
    }

    @Test
    public void getAllCompanyLevelsTest() {
        CompanyLevel cl1 = new CompanyLevel();
        cl1.setLevelValue(1);

        CompanyLevelDAO clDao = new CompanyLevelDAOImpl(emf);
        clDao.insert(cl1);

        int amount1 = clDao.getAllCompanyLevels().size();

        CompanyLevel cl2 = new CompanyLevel();
        cl2.setLevelValue(2);
        clDao.insert(cl2);

        int amount2 = clDao.getAllCompanyLevels().size();

        assertEquals(amount1, amount2 - 1);
    }
}

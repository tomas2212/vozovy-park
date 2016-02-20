package cz.muni.fi.pa165.vozovypark.DAOTests;

import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAO;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lukas Maticky
 */
public class CompanyLevelDAOTest extends AbstractDAOTest {

    @Autowired
    private CompanyLevelDAO companyLevelDao;

    @Test
    public void insertTest() {
        CompanyLevel cl = new CompanyLevel();
        cl.setLevelValue(1);

        companyLevelDao.insert(cl);

        CompanyLevel cl2 = companyLevelDao.getCompanyLevelById(cl.getId());

        assertEquals(cl, cl2);
    }

    @Test
    public void updateTest() {
        CompanyLevel cl1 = new CompanyLevel();
        cl1.setLevelValue(1);

        companyLevelDao.insert(cl1);

        CompanyLevel cl2 = companyLevelDao.getCompanyLevelById(cl1.getId());
        cl2.setLevelValue(2);
        companyLevelDao.update(cl2);

        CompanyLevel cl3 = companyLevelDao.getCompanyLevelById(cl1.getId());

        assertEquals(cl3.getLevelValue(), cl2.getLevelValue());
    }

    @Test
    public void deleteTest() {
        CompanyLevel cl1 = new CompanyLevel();
        cl1.setLevelValue(1);

        companyLevelDao.insert(cl1);

        CompanyLevel cl2 = companyLevelDao.getCompanyLevelById(cl1.getId());
        companyLevelDao.remove(cl2);

        assertNull(companyLevelDao.getCompanyLevelById(cl1.getId()));
    }

    @Test
    public void getCompanyLevelByIdTest() {
        CompanyLevel cl1 = new CompanyLevel();
        cl1.setLevelValue(1);

        companyLevelDao.insert(cl1);

        CompanyLevel cl2 = companyLevelDao.getCompanyLevelById(cl1.getId());

        assertEquals(cl1, cl2);
    }

    @Test
    public void getAllCompanyLevelsTest() {
        CompanyLevel cl1 = new CompanyLevel();
        cl1.setLevelValue(1);

        companyLevelDao.insert(cl1);

        int amount1 = companyLevelDao.getAllCompanyLevels().size();

        CompanyLevel cl2 = new CompanyLevel();
        cl2.setLevelValue(2);
        companyLevelDao.insert(cl2);

        int amount2 = companyLevelDao.getAllCompanyLevels().size();

        assertEquals(amount1, amount2 - 1);
    }
}

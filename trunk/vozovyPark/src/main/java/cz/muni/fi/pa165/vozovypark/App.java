package cz.muni.fi.pa165.vozovypark;

import cz.muni.fi.pa165.vozovypark.DAO.CarDAOImpl;
import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAO;
import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAOImpl;
import cz.muni.fi.pa165.vozovypark.DAO.EmployeeDAO;
import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import cz.muni.fi.pa165.vozovypark.service.CompanyLevelService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Project CarPark
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                  "applicationContext.xml"); //get Spring context 
        
        CompanyLevelService sv = (CompanyLevelService) ctx.getBean("CompanyLevelService");
        sv.createCompanyLevel("sdf");
  
         
    }
}

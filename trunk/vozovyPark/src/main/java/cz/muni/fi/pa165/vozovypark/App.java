package cz.muni.fi.pa165.vozovypark;

import cz.muni.fi.pa165.vozovypark.DAO.CarDAOImpl;
import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAO;
import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAOImpl;
import cz.muni.fi.pa165.vozovypark.DAO.EmployeeDAO;
import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Project CarPark
 *
 */
public class App 
{
    public static void main( String[] args )
    {
         EntityManagerFactory em = Persistence.createEntityManagerFactory("CarPark");
         EntityManager manager = em.createEntityManager();
         Employee employee = new Employee();
         CompanyLevel cl = new CompanyLevel();
         
         EmployeeDAO eDAO = new EmployeeDAO(manager); 
         Employee employeeById = eDAO.getEmployeeById(new Long(2));
//         System.out.println(employeeById.getName());
         
         CompanyLevelDAO cld = new CompanyLevelDAOImpl(manager);
         
         
         manager.getTransaction().begin();
         manager.persist(employee);
         employee.setName("Tomas");
         cl.setLevelValue(1);
         employee.setCompanyLevel(cl);
         manager.getTransaction().commit();
         
         
         Car car = new Car();
         car.setSpz("ZA980CD"); 
         
         manager.getTransaction().begin();         
         CarDAOImpl cdao = new CarDAOImpl(manager);
         cdao.insert(car);
         car.setModel("Novyy");
         manager.getTransaction().commit(); 
         
  
         
    }
}

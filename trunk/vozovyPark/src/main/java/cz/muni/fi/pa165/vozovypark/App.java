package cz.muni.fi.pa165.vozovypark;

import cz.muni.fi.pa165.vozovypark.DAO.CarDAO;
import cz.muni.fi.pa165.vozovypark.DAO.EmployeeDAO;
import cz.muni.fi.pa165.vozovypark.entities.Car;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
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
         
         EmployeeDAO eDAO = new EmployeeDAO(manager); 
         Employee employeeById = eDAO.getEmployeeById(new Long(2));
         System.out.println(employeeById.getName());
         
         /*
         manager.getTransaction().begin();
         manager.persist(employee);
         employee.setName("Tomas");
         manager.getTransaction().commit();
         */
         
         Car car = new Car();
         car.setSpz("ZA980CD"); 
         
         manager.getTransaction().begin();
         manager.persist(car);
         manager.getTransaction().commit();
      
        /* CarDAO cdao = new CarDAO(manager);
         Car carById
         cdao.insert(new Car());
         */
          
    }
}

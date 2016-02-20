package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import cz.muni.fi.pa165.vozovypark.entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lukas Maticky
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public Employee getEmployeeById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee getEmployeeByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("you must specify the name of employee");
        }
        Query q = entityManager.createNamedQuery(Employee.FIND_BY_NAME);
        q.setParameter("name", name);
        return (Employee) q.getSingleResult();
    }

    @Override
    public Employee getEmployeeByAddress(String address) {
        if (address == null) {
            throw new IllegalArgumentException("you must specify the address of employee");
        }
        Query q = entityManager.createNamedQuery(Employee.FIND_BY_ADDRESS);
        q.setParameter("address", address);
        return (Employee) q.getSingleResult();
    }

    @Override
    public void insert(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("you must specify company level");
        }
        entityManager.persist(employee);
    }

    public void remove(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("you must specify employee");
        }
        if (employee.getId() == null) {
            throw new IllegalArgumentException("cant update not persit entity");
        }
        entityManager.remove(entityManager.merge(employee));
    }

    @Override
    public void update(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("you must specify employee");
        }
        if (employee.getId() == null) {
            throw new IllegalArgumentException("cant update not persit entity");
        }
        entityManager.merge(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {

        TypedQuery<Employee> q = (TypedQuery<Employee>) entityManager.createQuery("SELECT e FROM Employee e");
        return q.getResultList();
    }

    @Override
    public List<Employee> getAllEmployeeWithHigherLevel(CompanyLevel companyLevel) {

        TypedQuery<Employee> q = entityManager.createQuery("SELECT e FROM Employee e INNER JOIN FETCH e.companyLevel as c WHERE (c.levelValue >= :companyLevelValue)", Employee.class);
        q.setParameter("companyLevelValue", companyLevel.getLevelValue());
        return q.getResultList();
    }

    @Override
    public Employee getEmployeeByLogin(String login) {
        if (login == null) {
            throw new IllegalArgumentException("you must specify the login of employee");
        }
        //TypedQuery q = entityManager.createNamedQuery(Employee.FIND_BY_LOGIN);
        TypedQuery<Employee> q = entityManager.createNamedQuery(Employee.FIND_BY_LOGIN, Employee.class);
        q.setParameter("login", login);
        List<Employee> resultList = q.getResultList();
        if (resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }
}

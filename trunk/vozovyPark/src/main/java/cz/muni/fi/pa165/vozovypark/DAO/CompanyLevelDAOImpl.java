package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andrej Bauer
 */
public class CompanyLevelDAOImpl implements CompanyLevelDAO {
    
    @PersistenceContext
    protected EntityManager entityManager;
    
    @Deprecated
    public CompanyLevelDAOImpl(EntityManagerFactory entityManager) {
       
    }
    
    public CompanyLevelDAOImpl(){
        
    }
    
    
    

    public void insert(CompanyLevel companyLevel) {
        if(companyLevel == null){
            throw new IllegalArgumentException("you must specify company level");
        }        
        entityManager.persist(companyLevel);
       
    }

    public void update(CompanyLevel companyLevel) {
        if(companyLevel == null){
            throw new IllegalArgumentException("you must specify company level");
        }
        if(companyLevel.getId() == null){
            throw new IllegalArgumentException("cant update not persit entity");
        }  
        entityManager.merge(companyLevel);
        
    }

    public void remove(CompanyLevel companyLevel) {
       if(companyLevel == null){
            throw new IllegalArgumentException("you must specify company level");
        }
        if(companyLevel.getId() == null){
            throw new IllegalArgumentException("cant rentityManagerove not persit entity");
        } 
        entityManager.remove(entityManager.merge(companyLevel));
       
    }

    public CompanyLevel getCompanyLevelById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("you must specify id of company level");
        }        
        return entityManager.find(CompanyLevel.class, id);
        
    }

    public List<CompanyLevel> getAllCompanyLevels() {        
        TypedQuery<CompanyLevel> q = entityManager.createQuery("SELECT c FROM CompanyLevel c", CompanyLevel.class);
        return q.getResultList();
    }
}

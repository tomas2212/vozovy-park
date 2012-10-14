/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author andrej
 */
public class CompanyLevelDAOImpl implements CompanyLevelDAO {
    
    EntityManagerFactory entityManagerFactory;

    public CompanyLevelDAOImpl(EntityManagerFactory factory) {
        this.entityManagerFactory = factory;
    }

    public void insert(CompanyLevel companyLevel) {
        if(companyLevel == null){
            throw new IllegalArgumentException("you must specify company level");
        }        
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(companyLevel);
        em.getTransaction().commit();
    }

    public void update(CompanyLevel companyLevel) {
        if(companyLevel == null){
            throw new IllegalArgumentException("you must specify company level");
        }
        if(companyLevel.getId() == null){
            throw new IllegalArgumentException("cant update not persit entity");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(companyLevel);
        em.getTransaction().commit();
    }

    public void remove(CompanyLevel companyLevel) {
       if(companyLevel == null){
            throw new IllegalArgumentException("you must specify company level");
        }
        if(companyLevel.getId() == null){
            throw new IllegalArgumentException("cant remove not persit entity");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(companyLevel));
        em.getTransaction().commit();
    }

    public CompanyLevel getCompanyLevelById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("you must specify id of company level");
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.find(CompanyLevel.class, id);
        
    }

    public List<CompanyLevel> getAllCompanyLevels() {
        EntityManager em = entityManagerFactory.createEntityManager();
        TypedQuery<CompanyLevel> q = em.createQuery("SELECT c FROM CompanyLevel c", CompanyLevel.class);
        return q.getResultList();
    }
}

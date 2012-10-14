/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author andrej
 */
public class CompanyLevelDAOImpl implements CompanyLevelDAO {
    
    EntityManager entityManager;

    public CompanyLevelDAOImpl(EntityManager em) {
        this.entityManager = em;
    }

    public void insert(CompanyLevel companyLevel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(CompanyLevel companyLevel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void remove(CompanyLevel companyLevel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public CompanyLevel getCompanyLevelById(Long Id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<CompanyLevel> getAllCompanyLevels() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

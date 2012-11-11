/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.ejb.dao;

import cz.muni.fi.pa165.ejb.entities.CompanyLevel;
import java.util.List;

/**
 * Dao for entity {@link CompanyLevel} provides data operations into database
 * @author Andrej Bauer
 */
public interface CompanyLevelDAO {
    
    /**
     * Create {@link CompanyLevel} in the database
     * @param companyLevel 
     */
    void insert(CompanyLevel companyLevel);
    
    /**
     * Update attributes of current {@link CompanyLevel} in database
     * Parameter companyLevel must be identified with his attribute id
     * @param companyLevel 
     */
    void update(CompanyLevel companyLevel);
    
    /**
     * Remove {@link CompanyLevel} given in parameter from database
     * Parameter companyLevel must be identified with his attribute id
     * @param companyLevel 
     */
    void remove(CompanyLevel companyLevel);
    
    /**
     * Finds {@link CompanyLevel} with given id in database
     * @param id
     * @return {@link CompanyLevel} if exist
     */
    CompanyLevel getCompanyLevelById(Long id);
    
    /**
     * Returns all  {@link CompanyLevel} entities from database
     * @return  {@link List} of all  {@link CompanyLevel} in database
     */
    List<CompanyLevel> getAllCompanyLevels();    
    
    CompanyLevel getRootCompanyLevel();
    
    Integer getMaxLevelValue();
    
    Integer getMinLevelValue();
    
    
    
    
}


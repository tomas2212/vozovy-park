/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.DAO;

import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import java.util.List;

/**
 *
 * @author andrej
 */
public interface CompanyLevelDAO {
    
    void insert(CompanyLevel companyLevel);
    
    void update(CompanyLevel companyLevel);
    
    void remove(CompanyLevel companyLevel);
    
    CompanyLevel getCompanyLevelById(Long id);
    
    List<CompanyLevel> getAllCompanyLevels();    
    
    
    
    
}

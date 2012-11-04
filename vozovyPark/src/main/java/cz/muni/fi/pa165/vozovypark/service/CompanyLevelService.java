package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import java.util.List;

/**
 *
 * @author Andrej Bauer
 */
public interface CompanyLevelService {
    
    CompanyLevelDTO createCompanyLevel(String CompanyLevelName);
    
    CompanyLevelDTO updateCompanyLevel(CompanyLevelDTO companyLevel);
    
    CompanyLevelDTO setCompanyLevelAsRoot(Long id);
    
    CompanyLevelDTO setCompanyLevelToPosition(Long id, Integer position);
    
    CompanyLevelDTO getCompanyLevelById(Long id);
    
    List<CompanyLevelDTO> getAllCompanyLeves();
    
    void removeCompanyLevel(Long id);
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.ejb.sevices;

import cz.muni.fi.pa165.ejb.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.ejb.dao.CompanyLevelDAO;
import cz.muni.fi.pa165.ejb.entities.CompanyLevel;
import cz.muni.fi.pa165.ejb.services.utils.Adapters;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author andrej
 */
@Stateless
@Local(value = CompanyLevelService.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CompanyLevelServiceImpl implements CompanyLevelService {

    @EJB
    CompanyLevelDAO companyLevelDao;

    public void setCompanyLevelDao(CompanyLevelDAO companyLevelDao) {
        this.companyLevelDao = companyLevelDao;
    }
    
    
    
    public CompanyLevelDTO createCompanyLevel(String companyLevelName) {
        if(companyLevelName == null){
            throw new IllegalArgumentException("CompanyLevel name is not specified");
        }
        Integer levelValue = 1;
        if(companyLevelDao.getMaxLevelValue() != null){
            levelValue = companyLevelDao.getMaxLevelValue() + 1;
        }
        CompanyLevel companyLevel = new CompanyLevel();
        companyLevel.setName(companyLevelName);
        companyLevel.setLevelValue(levelValue);
        companyLevelDao.insert(companyLevel);
        
        return Adapters.CompanyLevelEntityToDto(companyLevel);
    }

    public CompanyLevelDTO updateCompanyLevel(CompanyLevelDTO companyLevel) {
        if(companyLevel == null){
            throw new IllegalArgumentException("CompanyLevel name is not specified");
        }
        if(companyLevel.getId() == null){
            throw new IllegalArgumentException("CompanyLevel ID is not specified");
        }
        CompanyLevel entity = Adapters.CompanyLevelDtoToEntity(companyLevel);
        companyLevelDao.update(entity);
        return Adapters.CompanyLevelEntityToDto(entity);
        
    }

    public CompanyLevelDTO setCompanyLevelAsRoot(Long id) {
        if(id == null){
            throw new IllegalArgumentException("ID is not specified");
        }
        CompanyLevel companyLevel = companyLevelDao.getCompanyLevelById(id);
        if(companyLevel == null){
            throw new IllegalArgumentException("Company Level with specified ID does not exists");
        }
        Integer rootValue = companyLevelDao.getMinLevelValue();
        
        companyLevel.setLevelValue(rootValue - 1);
        companyLevelDao.update(companyLevel);
        return Adapters.CompanyLevelEntityToDto(companyLevel);
        
    }

    public CompanyLevelDTO setCompanyLevelToPosition(Long id, Integer position) {
        if(id == null){
            throw new IllegalArgumentException("ID is not specified");
        }
        CompanyLevel companyLevel = companyLevelDao.getCompanyLevelById(id);
        if(companyLevel == null){
            throw new IllegalArgumentException("Company Level with specified ID does not exists");
        }
        List<CompanyLevel> allCompanyLevels = companyLevelDao.getAllCompanyLevels();
        for(CompanyLevel cl : allCompanyLevels){
            if(cl.getLevelValue() >= position){
                cl.setLevelValue(cl.getLevelValue() + 1);
                companyLevelDao.update(cl);
            }
        }
        companyLevel.setLevelValue(position);
        companyLevelDao.update(companyLevel);
        
        return Adapters.CompanyLevelEntityToDto(companyLevel);
    }

    public CompanyLevelDTO getCompanyLevelById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("ID is not specified");
        }
        return Adapters.CompanyLevelEntityToDto(companyLevelDao.getCompanyLevelById(id));
    }

    public List<CompanyLevelDTO> getAllCompanyLeves() {
        List<CompanyLevelDTO> companyLevels = new ArrayList<CompanyLevelDTO>();
        List<CompanyLevel> allCompanyLevels = companyLevelDao.getAllCompanyLevels();
        for(CompanyLevel cl : allCompanyLevels){
            companyLevels.add(Adapters.CompanyLevelEntityToDto(cl));
        }
        return companyLevels;
    }

    public void removeCompanyLevel(Long id) {
        if(id == null){
            throw new IllegalArgumentException("ID is not specified");
        }
        CompanyLevel companyLevel = companyLevelDao.getCompanyLevelById(id);
        if(companyLevel == null){
            throw new IllegalArgumentException("Company Level with specified ID does not exists");
        }
        companyLevelDao.remove(companyLevel);
        
    }
    
    
    
}

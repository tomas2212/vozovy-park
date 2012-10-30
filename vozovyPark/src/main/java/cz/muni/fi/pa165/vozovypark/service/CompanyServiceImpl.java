/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.service;

import cz.muni.fi.pa165.vozovypark.DAO.CompanyLevelDAO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.entities.CompanyLevel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andrej
 */
public class CompanyServiceImpl implements CompanyLevelService {

    CompanyLevelDAO companyLevelDao;

    public void setCompanyLevelDao(CompanyLevelDAO companyLevelDao) {
        this.companyLevelDao = companyLevelDao;
    }
    
    
    
    public CompanyLevelDTO createCompanyLevel(String companyLevelName) {
        if(companyLevelName == null){
            throw new IllegalArgumentException("CompanyLevel name is not specified");
        }
        Integer levelValue = companyLevelDao.getMaxLevelValue() + 1;
        CompanyLevel companyLevel = new CompanyLevel();
        companyLevel.setName(companyLevelName);
        companyLevel.setLevelValue(levelValue);
        companyLevelDao.insert(companyLevel);
        
        return EntityToDtoAdapter(companyLevel);
    }

    public CompanyLevelDTO updateCompanyLevel(CompanyLevelDTO companyLevel) {
        if(companyLevel == null){
            throw new IllegalArgumentException("CompanyLevel name is not specified");
        }
        if(companyLevel.getId() == null){
            throw new IllegalArgumentException("CompanyLevel ID is not specified");
        }
        CompanyLevel entity = DtoToEntityAdapter(companyLevel);
        companyLevelDao.update(entity);
        return EntityToDtoAdapter(entity);
        
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
        return EntityToDtoAdapter(companyLevel);
        
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
        
        return EntityToDtoAdapter(companyLevel);
    }

    public CompanyLevelDTO getCompanyLevelById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("ID is not specified");
        }
        return EntityToDtoAdapter(companyLevelDao.getCompanyLevelById(id));
    }

    public List<CompanyLevelDTO> getAllCompanyLeves() {
        List<CompanyLevelDTO> companyLevels = new ArrayList<CompanyLevelDTO>();
        List<CompanyLevel> allCompanyLevels = companyLevelDao.getAllCompanyLevels();
        for(CompanyLevel cl : allCompanyLevels){
            companyLevels.add(EntityToDtoAdapter(cl));
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
    
    private CompanyLevelDTO EntityToDtoAdapter(CompanyLevel companyLevel){
        CompanyLevelDTO dto = new CompanyLevelDTO();
        dto.setId(companyLevel.getId());
        dto.setLevelValue(companyLevel.getLevelValue());
        dto.setName(companyLevel.getName());
        return dto;
    }
    
    private CompanyLevel DtoToEntityAdapter(CompanyLevelDTO companyLevel){
        CompanyLevel entity = new CompanyLevel();
        entity.setId(companyLevel.getId());
        entity.setLevelValue(companyLevel.getLevelValue());
        entity.setName(companyLevel.getName());
        return entity;
        
    }
    
}

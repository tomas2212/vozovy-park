/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Tomas Svrcek
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Car.FIND_ALL, query = "SELECT p from Car p"),
    @NamedQuery(name = Car.FIND_BY_ID, query = "SELECT p from Car p where p.id=:id"),
    @NamedQuery(name = Car.FIND_BY_SPZ, query = "SELECT p from Car p where p.spz=:spz") 
})
public class Car implements Serializable {
    
    public static final String FIND_ALL = "findAllCars";
    public static final String FIND_BY_ID = "findCarById";
    public static final String FIND_BY_SPZ = "findCarBySpz";
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(length=20)
    private Long id;
    @Column(length=15)
    private String spz;
    private String brand;
    private String model;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creationYear;
    private Boolean available;
    @OneToOne
    private CompanyLevel companyLevel;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpz() {
        return spz;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(Date creationYear) {
        this.creationYear = creationYear;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public CompanyLevel getCompanyLevel() {
        return companyLevel;
    }

    public void setCompanyLevel(CompanyLevel companyLevel) {
        this.companyLevel = companyLevel;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
    
}

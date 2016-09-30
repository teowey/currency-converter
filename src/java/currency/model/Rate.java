/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currency.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author teowey
 */
@Entity
public class Rate implements ConversionDTO, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Integer id;
    private String conversion;
    private double rate;
    private double value;
    /**
    * Creates a new instance of Account
    */
    public Rate() {
    }

    /*
    * Constructor used by CashierFacade to create a new entity.
    */
    public Rate(String conversion, double rate) {
        this.conversion = conversion;
        this.rate = rate;
    }

    @Override
    public Integer getId() {
        return id;
    }
    
    @Override
    public double getConversion(String conversion) {
        return rate;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Rate)) {
//            return false;
//        }
//        Rate other = (Rate) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "currency.model.ConversionEntity[ id=" + id + " ]";
//    }
//    
    
    public void setConversion(String conversion, double rate) {
        this.conversion = conversion;
        this.rate = rate;
    }
    
    public void setRate(double rate) {
        this.rate = rate;
    }
    
    public double getRate(String conversion) {
        return rate;
    }
    
    
//    /* Business logic */
//    @Override
//    public double convertTo(double amount, String conversion) {
//        
//         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    
}

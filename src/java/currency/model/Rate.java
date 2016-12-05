/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currency.model;

import java.io.Serializable;
//import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author teowey
 */
@Entity
public class Rate implements ConversionDTO, Serializable {

    
    @Id
    @GeneratedValue
    private Integer conversionId;
    
    private double rate;
    //private BigDecimal value;
    
    private String fromCurrency;
    private String toCurrency;
    /**
    * Creates a new instance of Account
    */
    public Rate() {
    }
    
    public Rate(Integer conversionId, String fromCurrency, String toCurrency, double rate) {
        this.conversionId = conversionId;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rate = rate;
        
    }

    @Override
    public Integer getConversionId() {
        return conversionId;
    }
    
    public void setConversionId(Integer conversionid) {
        this.conversionId = conversionid;
    }
    
    public void setRate(double rate) {
        this.rate = rate;
    }
    @Override
    public double getRate() {
        return rate;
    }
    
    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

}

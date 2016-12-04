/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currency.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
    private Integer conversionid;
    
    private double rate;
    private BigDecimal value;
    
    private String fromCur;
    private String toCur;
    /**
    * Creates a new instance of Account
    */
    public Rate() {
    }

    @Override
    public Integer getConversionId() {
        return conversionid;
    }
    
    public void setConversionId(Integer conversionid) {
        this.conversionid = conversionid;
    }
    @Override
    public BigDecimal getValue() {
        return value;
    }
    
    public void setRate(double rate) {
        this.rate = rate;
    }
    
    public double getRate(String conversion) {
        return rate;
    }
    
    public String getFromCur() {
        return fromCur;
    }

    public void setFromCur(String from) {
        this.fromCur = from;
    }

    public String getToCur() {
        return toCur;
    }

    public void setToCur(String to) {
        this.toCur = to;
    }

}

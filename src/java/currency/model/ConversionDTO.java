/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currency.model;

/**
 *
 * @author teowey
 */

/**/
/**
 * The views read-only view of an Conversion.
 */
public interface ConversionDTO {
    
    /* Gets the amount entered in the web page and 
    *  convert to the chosen conversion
    */
    double getConversion(String conversion);
    
    Integer getId();
    
    //double getValue();

    public double getRate(String conversion);
    
}

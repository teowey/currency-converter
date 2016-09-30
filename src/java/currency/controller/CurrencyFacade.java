 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currency.controller;

import currency.model.ConversionDTO;
import currency.model.Rate;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author teowey
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class CurrencyFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "currencyPU")
    private EntityManager em;
    
    public ConversionDTO createConversion(String conversion, double rate) {
        Rate newConversion = new Rate(conversion, rate);
        em.persist(newConversion);
        return newConversion;
    }
    
    public ConversionDTO findConversion(String conversion) {
        ConversionDTO found = em.find(Rate.class, conversion);
        if (found == null) {
            throw new EntityNotFoundException("No conversion with " + conversion);
        }
        return found;
    }
    
    public double convertTo(double amount, String conversion) {
        ConversionDTO found = em.find(Rate.class, conversion);
        double converted = amount * found.getRate(conversion);
        return converted;
    }
}

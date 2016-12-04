/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currency.controller;

import currency.model.ConversionDTO;
import currency.model.Rate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.Currency;
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

    public void addConversionRate(Double rate, String fromCur, String toCur) {
        Rate conversionRate = new Rate();
        conversionRate.setRate(rate);
        conversionRate.setFromCur(fromCur);
        conversionRate.setToCur(toCur);

        em.persist(conversionRate);
    }

    public BigDecimal convertTo(BigDecimal value, String from, String to) {
        
        // changed to validate the currency code - note BRA will fail - need to change to BRL - remove this code if you dont need it.
        Currency fromCurr = Currency.getInstance(from);
        Currency toCurr = Currency.getInstance(to);
        
        // debugging code - to help troubleshoot (only use this on small table) - will print out the contents of the table so you can see if data is not correct
        List<Rate> rates = em.createQuery("SELECT c FROM Rate c", Rate.class).getResultList();
        if (rates == null || rates.isEmpty()) {
            throw new RuntimeException("rates table is empty");
        }

        for (Rate r : rates) {
            // push to sysout or whatever logger you are using
            System.out.println(String.format("Rate:fromCur[%s], toCur[%s]", r.getFromCur(), r.getToCur()));
        }
        // end debugging code - remove when no longer needed.

        // changed the query to use bind parameters - :fromCurrency, :toCurrency
        TypedQuery<Rate> query = em.createQuery(""
                + "SELECT c FROM Rate c WHERE c.fromCur = :fromCurrency AND c.toCur = :toCurrency", Rate.class);

        query.setParameter("fromCurrency", fromCurr.getCurrencyCode());
        query.setParameter("toCurrency", toCurr.getCurrencyCode());

        Rate rate = query.getSingleResult();

        if (rate == null) {
            throw new EntityNotFoundException("Can't find rate");
        }

        BigDecimal converted =
            value.multiply(rate.getValue()).setScale(toCurr.getDefaultFractionDigits(), RoundingMode.HALF_DOWN);
    
        return converted;

    }
}

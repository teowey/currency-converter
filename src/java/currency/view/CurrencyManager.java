/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currency.view;

import currency.model.ConversionDTO;
import currency.controller.CurrencyFacade;
import java.io.Serializable;
//import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author teowey
 */

/* The ConversationScoped annotation specifies that 
the bean exists during a conversation. A conversation 
must be started and stopped manually but can not exceed 
the length of the Http session. Furthermore, conversations 
are thread-safe, meaning that even if you started two concurrent 
requests, two different conversations would still result. 
This is the main reason why conversation scope is used instead of session scope here.*/
@Named("currencyManager")
@ApplicationScoped
public class CurrencyManager implements Serializable {

    private static final long serialVersionUID = 16247164405L;
    @EJB
    private CurrencyFacade currencyFacade;

    @Inject
    private Conversation conversation;

    private Exception conversionFailure;
    private String fromCur;
    private String toCur;
    private Double rate;
    private String allRates;
    private double inputAmount;
    private int id;
    private double convertedAmount;
    private static boolean init = false;

    private Exception exception;
    
    public CurrencyManager() {

    }

    private void startConversation() {
        if (!init) {
            init = true;

            try {
                currencyFacade.addConversionRate(1, "BRL", "EUR", 0.27);
                currencyFacade.addConversionRate(2, "BRL", "SEK", 2.65);
                currencyFacade.addConversionRate(3, "BRL", "USD", 0.28);
                
                currencyFacade.addConversionRate(4, "EUR", "BRL", 3.68);
                currencyFacade.addConversionRate(5, "EUR", "SEK", 9.78);
                currencyFacade.addConversionRate(6, "EUR", "USD", 1.06);
                
                currencyFacade.addConversionRate(7, "SEK", "BRL", 0.37);
                currencyFacade.addConversionRate(8, "SEK", "EUR", 0.10);
                currencyFacade.addConversionRate(9, "SEK", "USD", 0.10);
                
                currencyFacade.addConversionRate(10, "USD", "BRL", 3.47);
                currencyFacade.addConversionRate(11, "USD", "EUR", 0.94);
                currencyFacade.addConversionRate(12, "USD", "SEK", 9.22);

            } catch (Exception e) {

            }
        }
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace();
        conversionFailure = e;
    }

    /**
     * Returns <code>false</code> if the latest transaction failed, otherwise
     * <code>true</code>.
     *
     * @return
     */
    public boolean getSuccess() {
        return conversionFailure == null;
    }

    /**
     * Returns the latest thrown exception.
     *
     * @return
     */
    public Exception getException() {
        return conversionFailure;
    }

    // Methods to be used on the web page index.xhtml
    public double getInputAmount() {
        return inputAmount;
    }

    public void setInputAmount(double inputAmount) {
        this.inputAmount = inputAmount;
    }

    public int getId() {
        return id;
    }

    public void setConversion(int id) {
        this.id = id;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public String getFromCur() {
        return fromCur;
    }

    public void setFromCur(String fromCur) {
        this.fromCur = fromCur;
    }

    public String getToCur() {
        return toCur;
    }

    public void setToCur(String toCur) {
        this.toCur = toCur;
    }

    // Button logic
    public void convertTo() {
        startConversation();
        conversionFailure = null;

        if (getFromCur().equals("") || getToCur().equals("")) {
            handleException(new Exception("Enter both currencies!"));
            return;
        } else if (getFromCur().equals(getToCur())) {
            handleException(new Exception("Currencies can't be identical!"));
            return;
        }
        if (inputAmount == 0) {
            handleException(new Exception("No amount entered!"));
            return;
        }
        try {
            convertedAmount = currencyFacade.convertTo(inputAmount, fromCur, toCur);
        } 
        catch (Exception e) {
            stopConversation();
            e.printStackTrace();
            exception = e;
        } 
        finally {
            stopConversation();
        }
    }
    
    public void viewRates() {
        startConversation();
        conversionFailure = null;
        
        try {
            allRates = currencyFacade.viewRates();
        }
        catch (Exception e) {
            handleException(e);
        }
    }
    
    public String getAllRates() {
        return allRates;
    }
    
    public void setAllRates(String allRates) {
        this.allRates = allRates;
    }
}

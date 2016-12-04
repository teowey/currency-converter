/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currency.view;

import currency.model.ConversionDTO;
import currency.controller.CurrencyFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
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
@ConversationScoped
public class CurrencyManager implements Serializable {

    private static final long serialVersionUID = 16247164405L;
    @EJB
    private CurrencyFacade currencyFacade;
    
    private BigDecimal inputAmount;
    private int id;
    private BigDecimal convertedAmount;

    @Inject
    private Conversation conversation;
    private Exception conversionFailure;
    private String fromCur;
    private String toCur;

    private void startConversation() {
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
        e.printStackTrace(System.err);
        conversionFailure = e;
    }

    /**
     * Returns <code>false</code> if the latest transaction failed, otherwise
     * <code>true</code>.
     * @return 
     */
    public boolean getSuccess() {
        return conversionFailure == null;
    }

    /**
     * Returns the latest thrown exception.
     * @return 
     */
    public Exception getException() {
        return conversionFailure;
    }

    // Methods to be used on the web page index.xhtml
    public BigDecimal getInputAmount() {
        return inputAmount;
    }

    public void setInputAmount(BigDecimal inputAmount) {
        this.inputAmount = inputAmount;
    }

    public int getId() {
        return id;
    }

    public void setConversion(int id) {
        this.id = id;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(BigDecimal convertedAmount) {
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
        try {
            convertedAmount = currencyFacade.convertTo(inputAmount, fromCur, toCur);
        } 
        catch (Exception e) {
            stopConversation();
            handleException(e);
        }
    }
}

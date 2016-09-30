/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currency.view;

import currency.model.ConversionDTO;
import currency.controller.CurrencyFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
    private double currentConversion;
    private double inputAmount;
    private String conversion;
    private double convertedAmount;

    @Inject
    private Conversation conversation;
    private Exception conversionFailure;

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
     */
    public boolean getSuccess() {
        return conversionFailure == null;
    }

    /**
     * Returns the latest thrown exception.
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

    public String getConversion() {
        return conversion;
    }

    public void setConversion(String conversion) {
        this.conversion = conversion;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    // Button logic
    private void convertTo() {
        startConversation();
        conversionFailure = null;
        try {
            convertedAmount = currencyFacade.convertTo(inputAmount, conversion);
        } catch (Exception e) {
            stopConversation();
            handleException(e);
        }
    }
}

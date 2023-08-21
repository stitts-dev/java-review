package com.stitts.oop_review.model.payment;

import java.math.BigDecimal;

public class PaymentResponse {

    private int result;
    private String errorCde;
    private String resultMessage;
    private BigDecimal paymentCostAmount;

    public PaymentResponse(){

    }

    public PaymentResponse(int result, String errorCde, String resultMessage, BigDecimal paymentCostAmount) {
        this.result = result;
        this.errorCde = errorCde;
        this.resultMessage = resultMessage;
        this.paymentCostAmount = paymentCostAmount;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getErrorCde() {
        return errorCde;
    }

    public void setErrorCde(String errorCde) {
        this.errorCde = errorCde;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public BigDecimal getPaymentCostAmount() {
        return paymentCostAmount;
    }

    public void setPaymentCostAmount(BigDecimal paymentCostAmount) {
        this.paymentCostAmount = paymentCostAmount;
    }
}

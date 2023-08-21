package com.stitts.oop_review.model;

import java.math.BigDecimal;

public class AbstractPosClientRequest{
    private final BigDecimal AMOUNT_CONVERSATION_VARIABLE = new BigDecimal(100);

    protected BigDecimal requestedAmount;
    protected String orderId;

    public AbstractPosClientRequest(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount.multiply(AMOUNT_CONVERSATION_VARIABLE);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}

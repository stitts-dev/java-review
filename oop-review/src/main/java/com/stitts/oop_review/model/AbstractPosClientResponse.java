package com.stitts.oop_review.model;

import java.math.BigDecimal;

public class AbstractPosClientResponse {

    protected int result;
    protected String errorCde;
    protected BigDecimal amount;

    public AbstractPosClientResponse(int result, String errorCde, BigDecimal amount) {
        this.result = result;
        this.errorCde = errorCde;
        this.amount = amount;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

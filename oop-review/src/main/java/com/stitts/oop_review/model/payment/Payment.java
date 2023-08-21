package com.stitts.oop_review.model.payment;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Payment {
    private Date paymentDate;
    private BigDecimal amount;
    private UUID orderId;
    private BigDecimal finalAmount;

    public Payment(Date paymentDate, BigDecimal amount, UUID orderId, BigDecimal finalAmount) {
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.orderId = orderId;
        this.finalAmount = finalAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
    }
}

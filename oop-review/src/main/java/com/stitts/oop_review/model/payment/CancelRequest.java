package com.stitts.oop_review.model.payment;

import com.stitts.oop_review.enums.Localization;

import java.math.BigDecimal;

public class CancelRequest extends AbstractPaymentRequest {
    public CancelRequest(BigDecimal amount, Localization locale, String conversationId) {
        super(amount, locale, conversationId);
    }
}

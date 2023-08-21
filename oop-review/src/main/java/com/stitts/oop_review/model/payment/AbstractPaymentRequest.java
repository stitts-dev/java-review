package com.stitts.oop_review.model.payment;


import com.stitts.oop_review.enums.Localization;

import java.math.BigDecimal;
import java.util.Arrays;

public class AbstractPaymentRequest {

    public static final Localization DEFAULT_LOCALE = Localization.ENG;

    protected BigDecimal amount;
    protected Localization locale;
    protected String orderId;

    public AbstractPaymentRequest(BigDecimal amount, Localization locale, String orderId) {
        this.amount = amount;
        this.locale = locale;
        this.orderId = orderId;
    }

    public Localization getLocale() {
        return Arrays.stream(Localization.values()).
                filter(localization ->
                        localization.name().equals(this.locale.name())).findAny().orElse(DEFAULT_LOCALE);
    }

    public void setLocale(Localization locale) {
        this.locale = locale;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

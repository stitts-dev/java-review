package com.stitts.oop_review.model.payment;

import com.stitts.oop_review.enums.CardType;
import com.stitts.oop_review.enums.Localization;
import com.stitts.oop_review.enums.ServiceProvider;
import com.stitts.oop_review.helper.ObjectHelper;

import java.math.BigDecimal;

public class AuthRequest extends AbstractPaymentRequest {

    private ServiceProvider serviceProvider;
    private String posName;
    private CardType cardType;
    private Integer installment;
    private boolean hybridPayment;

    public AuthRequest(BigDecimal amount,
                       Localization locale,
                       String conversationId,
                       ServiceProvider serviceProvider,
                       String posName,
                       CardType cardType,
                       Integer installment) {

        super(amount, locale, conversationId);
        this.serviceProvider = serviceProvider;
        this.posName = posName;
        this.cardType = cardType;
        this.installment = installment;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }


    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Integer getInstallment() {
        if (ObjectHelper.isNotPresent(installment)){
            return 1;
        }
        return installment;
    }

    public void setInstallment(Integer installment) {
        this.installment = installment;
    }

    // decide payment will be processed as hybrid or tenant
    public boolean isHybridPayment() {
        if (hybridPayment && ObjectHelper.isPresent(posName)){
            return Boolean.TRUE;
        }else
            return Boolean.FALSE;
    }

    public void setHybridPayment(boolean hybridPayment) {
        this.hybridPayment = hybridPayment;
    }
}

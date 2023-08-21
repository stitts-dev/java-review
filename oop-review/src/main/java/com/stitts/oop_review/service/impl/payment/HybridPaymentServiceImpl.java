package com.stitts.oop_review.service.impl.payment;


import com.stitts.oop_review.enums.ServiceProvider;
import com.stitts.oop_review.model.payment.PaymentResponse;
import com.stitts.oop_review.model.payment.AuthRequest;
import com.stitts.oop_review.model.payment.Payment;
import com.stitts.oop_review.model.pos.Pos;
import com.stitts.oop_review.model.pos.PosClientRequest;
import com.stitts.oop_review.model.pos.PosClientResponse;
import com.stitts.oop_review.posclient.AbstractPosClient;
import com.stitts.oop_review.posclient.client.BankAPosClient;
import com.stitts.oop_review.posclient.client.BankBPosClient;
import com.stitts.oop_review.posclient.client.BankCPosClient;
import com.stitts.oop_review.service.PaymentService;
import com.stitts.oop_review.service.PosSelectionService;
import com.stitts.oop_review.service.impl.selection.HybridPosSelectionServiceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import static com.stitts.oop_review.constant.StaticConstants.HYBRID_MERCHANT_PAYMENT_LIST;


public class HybridPaymentServiceImpl implements PaymentService {

    @Override
    public PaymentResponse auth(AuthRequest authRequest) {

        PosSelectionService posSelectionService = new HybridPosSelectionServiceImpl();

        Pos pos = posSelectionService.decidePaymentPos(authRequest);

        AbstractPosClient abstractPosClient = decidePosClient(pos.getName());

        PosClientRequest posClientRequest = new PosClientRequest(authRequest.getAmount());

        UUID orderId = abstractPosClient.generateOrderId();
        posClientRequest.setOrderId(orderId.toString());

        PosClientResponse posClientResponse = abstractPosClient.auth(posClientRequest);
        PaymentResponse paymentResponse = new PaymentResponse();

        paymentResponse.setResult(posClientResponse.getResult());
        paymentResponse.setErrorCde(posClientResponse.getErrorCde());

        paymentResponse.setPaymentCostAmount(
                calculateCommissionAmountForHybridMerchant(authRequest.getServiceProvider()));

        if (paymentResponse.getResult() == 1){
            initHybridMerchantPayment(authRequest, orderId);
        }

        return paymentResponse;
    }


    @Override
    public PaymentResponse auth3D(AuthRequest auth3DRequest){

        PosSelectionService posSelectionService = new HybridPosSelectionServiceImpl();

        Pos pos = posSelectionService.decidePaymentPos(auth3DRequest);

        AbstractPosClient abstractPosClient = decidePosClient(pos.getName());

        PosClientRequest posClientRequest = new PosClientRequest(auth3DRequest.getAmount());

        UUID orderId = abstractPosClient.generateOrderId();
        posClientRequest.setOrderId(orderId.toString());

        PosClientResponse posClientResponse = abstractPosClient.auth3D(posClientRequest);

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setResult(posClientResponse.getResult());
        paymentResponse.setErrorCde(posClientResponse.getErrorCde());

        paymentResponse.setPaymentCostAmount(
                calculateCommissionAmountForHybridMerchant(auth3DRequest.getServiceProvider()));

        if (paymentResponse.getResult() == 1){
            initHybridMerchantPayment(auth3DRequest, orderId);
        }

        return paymentResponse;
    }

    // cost calculation showing how much will be charged for successful payments from stores
    public BigDecimal calculateCommissionAmountForHybridMerchant(ServiceProvider serviceProvider) {
        switch (serviceProvider){
            case AMEX:
                return BigDecimal.TEN;
            case VISA:
                return new BigDecimal("0.2");
            case MASTER_CARD:
                return new BigDecimal("0.3");
            default:
                return BigDecimal.ZERO;
        }
    }

    // after succesfull payment, payments need to be collected into payment list
    private void initHybridMerchantPayment(AuthRequest authRequest, UUID orderId){
        Payment payment = new Payment(new Date(), authRequest.getAmount(), orderId, authRequest.getAmount());
        HYBRID_MERCHANT_PAYMENT_LIST.add(payment);
    }

    //determines which bank the payments are directed to
    public AbstractPosClient decidePosClient(String posName){
        switch (posName){
            case "BANKA":
                return new BankAPosClient();
            case "BANKB":
                return new BankBPosClient();
            case "BANKC":
                return new BankCPosClient();
            default:
                return null;
        }
    }
}

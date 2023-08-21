package com.stitts.oop_review.service.impl.payment;

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
import com.stitts.oop_review.service.impl.selection.TenantPosSelectionServiceImpl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

import static com.stitts.oop_review.constant.StaticConstants.CYDEO_PAYMENT_LIST;


public class TenantPaymentServiceImpl implements PaymentService {

    private final ResourceBundle resourceBundle;

    public TenantPaymentServiceImpl(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public PaymentResponse auth(AuthRequest authRequest){
        PosSelectionService posSelectionService = new TenantPosSelectionServiceImpl();

        Pos pos = posSelectionService.decidePaymentPos(authRequest);

        AbstractPosClient abstractPosClient = decidePosClient(pos.getName());

        UUID orderId = abstractPosClient.generateOrderId();

        PosClientRequest posClientRequest = new  PosClientRequest(authRequest.getAmount());

        posClientRequest.setOrderId(orderId.toString());

        PosClientResponse posClientResponse = abstractPosClient.auth(posClientRequest);

        PaymentResponse paymentResponse = doErrorCodeMapping(posClientResponse, resourceBundle);

        paymentResponse.setPaymentCostAmount(calculateCommissionForTenantMerchant(
                authRequest.getAmount(), pos.getInstallmentCommissionMap().get(authRequest.getInstallment())));

        if (paymentResponse.getResult() == 1){
            initPaymentRecord(authRequest, orderId);
        }
        return paymentResponse;
    }


    @Override
    public PaymentResponse auth3D(AuthRequest auth3DRequest) {

        PosSelectionService posSelectionService = new TenantPosSelectionServiceImpl();

        Pos pos = posSelectionService.decidePaymentPos(auth3DRequest);

        AbstractPosClient abstractPosClient = decidePosClient(pos.getName());

        PosClientRequest posClientRequest = new PosClientRequest(auth3DRequest.getAmount());

        UUID orderId = abstractPosClient.generateOrderId();

        posClientRequest.setOrderId(orderId.toString());

        PosClientResponse posClientResponse = abstractPosClient.auth3D(posClientRequest);

        PaymentResponse paymentResponse = doErrorCodeMapping(posClientResponse, resourceBundle);

        if (paymentResponse.getResult() == 1){
            paymentResponse.setPaymentCostAmount(calculateCommissionForTenantMerchant(
                    auth3DRequest.getAmount(), pos.getInstallmentCommissionMap().get(auth3DRequest.getInstallment())));

            initPaymentRecord(auth3DRequest, orderId);
        }

        return paymentResponse;
    }

    public BigDecimal  calculateCommissionForTenantMerchant(BigDecimal paymentAmount, Double commissionRate) {
        return paymentAmount.multiply(new BigDecimal(commissionRate))
                .divide(new BigDecimal(100))
                .round(MathContext.DECIMAL32);
    }

    PaymentResponse doErrorCodeMapping(PosClientResponse posClientResponse, ResourceBundle resourceBundle){

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setResult(posClientResponse.getResult());

        if (posClientResponse.getResult() != 1){
            paymentResponse.
                    setResultMessage(resourceBundle.getString(
                            resourceBundle.getString("error.code." + posClientResponse.getErrorCde())));

            paymentResponse.setErrorCde(posClientResponse.getErrorCde());
        }
        return paymentResponse;
    }

    public void initPaymentRecord(AuthRequest authRequest, UUID orderId){
        Payment payment = new Payment(new Date(), authRequest.getAmount(),
                orderId, authRequest.getAmount());
        CYDEO_PAYMENT_LIST.add(payment);
    }

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

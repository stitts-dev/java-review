package com.stitts.oop_review.service;


import com.stitts.oop_review.model.payment.PaymentResponse;
import com.stitts.oop_review.model.payment.AuthRequest;

public interface PaymentService {
    PaymentResponse auth(AuthRequest authRequest);

    PaymentResponse auth3D(AuthRequest auth3DRequest);
}

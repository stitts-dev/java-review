package com.stitts.oop_review.service;

import com.stitts.oop_review.model.payment.AuthRequest;
import com.stitts.oop_review.model.pos.Pos;

public interface PosSelectionService {
    Pos decidePaymentPos(AuthRequest authRequest);
}

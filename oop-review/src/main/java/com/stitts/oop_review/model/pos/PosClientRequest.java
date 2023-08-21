package com.stitts.oop_review.model.pos;


import com.stitts.oop_review.model.AbstractPosClientRequest;

import java.math.BigDecimal;

public class PosClientRequest extends AbstractPosClientRequest {
    public PosClientRequest(BigDecimal requestedAmount) {
        super(requestedAmount);
    }
}

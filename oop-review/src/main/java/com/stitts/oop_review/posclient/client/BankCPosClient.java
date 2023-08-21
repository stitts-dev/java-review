package com.stitts.oop_review.posclient.client;


import com.stitts.oop_review.model.pos.PosClientRequest;
import com.stitts.oop_review.model.pos.PosClientResponse;
import com.stitts.oop_review.posclient.AbstractPosClient;

public class BankCPosClient extends AbstractPosClient {

    // return dummy response
    @Override
    public PosClientResponse auth(PosClientRequest posClientRequest) {
        return new PosClientResponse(0, "1345", posClientRequest.getRequestedAmount());
    }

    // return dummy response
    @Override
    public PosClientResponse auth3D(PosClientRequest posClientRequest) {
        return new PosClientResponse(0, "23", posClientRequest.getRequestedAmount());
    }

}

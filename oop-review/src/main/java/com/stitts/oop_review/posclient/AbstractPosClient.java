package com.stitts.oop_review.posclient;

import com.stitts.oop_review.model.pos.PosClientRequest;
import com.stitts.oop_review.model.pos.PosClientResponse;

import java.util.UUID;

public abstract class AbstractPosClient {

    public UUID generateOrderId(){
        return UUID.randomUUID();
    }

    //calls any banks auth api
    public abstract PosClientResponse auth(PosClientRequest posClientRequest);

    //calls any banks auth3D api
    public abstract PosClientResponse auth3D(PosClientRequest posClientRequest);


}

package com.stitts.oop_review.service.impl.selection;

import com.stitts.oop_review.constant.StaticConstants;
import com.stitts.oop_review.model.payment.AuthRequest;
import com.stitts.oop_review.model.pos.Pos;
import com.stitts.oop_review.service.PosSelectionService;

import java.util.List;
import java.util.stream.Collectors;

public class TenantPosSelectionServiceImpl implements PosSelectionService {
    @Override
    // We filter the POS that can serve the service provider of the card.
    // In this way, we can choose the pos with the lowest cost among all the options.
    public Pos decidePaymentPos(AuthRequest authRequest) {

        List<Pos> avaliablePosList = StaticConstants.POS_LIST.stream()
        .filter(pos -> pos.getSupportedServiceProviderList().contains(authRequest.getServiceProvider())).collect(Collectors.toList());

        return decideSelectedPos(avaliablePosList, authRequest);
    }

    //We aim to determine the lowest cost pos from the list of
    // pos where payment can be accepted and send the payment to this pos.
    public Pos decideSelectedPos(List<Pos> posList, AuthRequest authRequest){
        Pos selectedPos = posList.get(0);
        double firstIndexCommission = selectedPos.getInstallmentCommissionMap().get(authRequest.getInstallment()) ;
        for (Pos pos : posList){
            if (!pos.getName().equals(selectedPos.getName())){
                double posCommissionAmount = pos.getInstallmentCommissionMap().get(authRequest.getInstallment());
                if (posCommissionAmount < firstIndexCommission){
                    selectedPos = pos;
                }
            }
        }
        return selectedPos;
    }
}

package com.stitts.oop_review.constant;


import com.stitts.oop_review.model.payment.Payment;
import com.stitts.oop_review.model.pos.Pos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class StaticConstants {
    public static final Long CYDEO_PARTNER_ID = 1L;

    public static final Long EXTERNAL_PARTNER_ID = 2L;

    public static final List<UUID> ORDER_ID_LIST = new ArrayList<>();

    public static final List<Pos> POS_LIST = new ArrayList<>();

    public static List<Integer> AVAILABLE_INSTALLMENT = Arrays.asList(1,2,3,4,5,6,7);

    public static List<Character> BANK_CODE = Arrays.asList('A', 'B', 'C');

    public static List<Character> EXTERNAL_BANK_CODE = Arrays.asList('D', 'E', 'F');

    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final List<Payment> CYDEO_PAYMENT_LIST = new ArrayList<>();

    public static final List<Payment> HYBRID_MERCHANT_PAYMENT_LIST = new ArrayList<>();
}

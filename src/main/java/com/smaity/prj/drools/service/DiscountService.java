package com.smaity.prj.drools.service;

import com.smaity.prj.drools.domain.CartBill;
import com.smaity.prj.drools.domain.DiscountedBillDetail;

public interface DiscountService {
    DiscountedBillDetail getDiscountedBill(CartBill cartBill) throws Exception;
}

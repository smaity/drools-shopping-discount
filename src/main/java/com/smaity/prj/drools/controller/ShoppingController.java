package com.smaity.prj.drools.controller;
/**
 * Controller File
 *
 * @Author: Sajal Maity
 * @Date: 05/31/2020
 */

import com.smaity.prj.drools.domain.CartBill;
import com.smaity.prj.drools.domain.DiscountedBillDetail;
import com.smaity.prj.drools.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingController {
    @Autowired
    @Qualifier("christmasDiscount")
    private DiscountService christmasDiscountService;

    @PostMapping(value = "/getChristmasDiscountedBill", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DiscountedBillDetail getBillingDetail(@RequestBody CartBill cartBill) throws Exception {
        return christmasDiscountService.getDiscountedBill(cartBill);
    }
}

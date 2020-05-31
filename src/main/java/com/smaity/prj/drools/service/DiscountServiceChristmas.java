package com.smaity.prj.drools.service;
/**
 * Christmas Discount Service File
 * <p>
 * Drools rule engine is applied over various kind of customer types and discount criteria
 *
 * @Author: Sajal Maity
 * @Date: 05/31/2020
 */

import com.smaity.prj.drools.domain.CartBill;
import com.smaity.prj.drools.domain.DiscountedBillDetail;
import com.smaity.prj.drools.exception.ServiceException;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("christmasDiscount")
public class DiscountServiceChristmas implements DiscountService {
    @Autowired
    private KieContainer kContainer;

    @Override
    public DiscountedBillDetail getDiscountedBill(CartBill cartBill) throws Exception {
        try {
            KieSession kieSession = kContainer.newKieSession();
            kieSession.insert(cartBill);
            kieSession.fireAllRules();
            kieSession.dispose();
            return new DiscountedBillDetail(cartBill.getCartId(), cartBill.getBaseAmount().subtract(cartBill.getDiscountedAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));
        } catch (Exception ex) {
            throw new ServiceException("Failed to get discount details.");
        }
    }
}

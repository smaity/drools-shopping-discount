package com.smaity.prj.drools.domain;
/**
 * Discount Bill Domain File
 * <p>
 * Define discounted bills and related information
 *
 * @Author: Sajal Maity
 * @Date: 05/31/2020
 */

import java.math.BigDecimal;

public class DiscountedBillDetail {
    Long cartId;
    BigDecimal totalAmount;

    public DiscountedBillDetail(Long cartId, BigDecimal totalAmount) {
        this.cartId = cartId;
        this.totalAmount = totalAmount;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}

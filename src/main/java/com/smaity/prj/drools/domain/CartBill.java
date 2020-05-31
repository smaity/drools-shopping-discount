package com.smaity.prj.drools.domain;
/**
 * Cart Bill Domain File
 *
 * Rules will be applied over this domain object
 *
 * @Author: Sajal Maity
 * @Date: 05/31/2020
 */

import java.math.BigDecimal;

public class CartBill {
    private Long cartId;
    private CustomerType customerType;
    private BigDecimal baseAmount;
    private BigDecimal discountedAmount;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    public BigDecimal getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(BigDecimal discountedAmount) {
        this.discountedAmount = discountedAmount;

    }
}

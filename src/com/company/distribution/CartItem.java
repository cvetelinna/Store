package com.company.distribution;

import com.company.products.BaseProduct;
import com.company.products.PerishableProduct;

import java.math.BigDecimal;

public class CartItem {
    private final BigDecimal quantity;
    private final BaseProduct product;

    public CartItem(BaseProduct product, BigDecimal quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BaseProduct getProduct() {
        return this.product;
    }

    public BigDecimal getTotalPrice() {
        return this.product.getPrice().multiply(this.quantity);
    }
}

package com.company.products;

import java.math.BigDecimal;

public abstract class BaseProduct {
    private String name;
    private String brand;
    private BigDecimal price;

    public abstract BigDecimal getDiscountedPrice();

    public BigDecimal getPrice() {
        return price;
    }
}

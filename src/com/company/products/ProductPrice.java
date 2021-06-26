package com.company.products;

import java.math.BigDecimal;

public class ProductPrice {
    private final BigDecimal originalPrice;
    private final BigDecimal discountAmount;
    private final BigDecimal discountPercentage;
    private final boolean isDiscounted;

    public ProductPrice(
            BigDecimal originalPrice,
            BigDecimal discountAmount,
            BigDecimal discountPercentage,
            boolean isDiscounted) {
        this.originalPrice = originalPrice;
        this.discountAmount = discountAmount;
        this.discountPercentage = discountPercentage;
        this.isDiscounted = isDiscounted;
    }

    public ProductPrice(
            BigDecimal originalPrice
    ) {
        this.originalPrice = originalPrice;
        this.discountAmount = new BigDecimal("0");
        this.discountPercentage = new BigDecimal("0");
        this.isDiscounted = false;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public boolean isDiscounted() {
        return isDiscounted;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }
}

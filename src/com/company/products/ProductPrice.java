package com.company.products;

import java.math.BigDecimal;

public class ProductPrice {
    private final BigDecimal originalPrice;
    private final BigDecimal discountAmount;
    private final BigDecimal discountPercentage;
    private final boolean isDiscounted;
    private boolean isDiscounted;

    public ProductPrice(
            BigDecimal originalPrice,
            BigDecimal discountedPrice,
            BigDecimal discountAmount,
            BigDecimal discountPercentage,
            boolean isDiscounted) {
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.discountAmount = discountAmount;
        this.discountPercentage = discountPercentage;
        this.isDiscounted = isDiscounted;
    }

    public ProductPrice(
            BigDecimal originalPrice
    ) {
        this.originalPrice = originalPrice;
        this.discountedPrice = new BigDecimal("0");
        this.discountAmount = new BigDecimal("0");
        this.discountPercentage = new BigDecimal("0");
        this.isDiscounted = false;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
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

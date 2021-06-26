package com.company.products;

import com.company.enums.ProductTypeEnum;
import com.company.utils.Constants;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

public class PerishableProduct extends BaseProduct {
    private Instant expirationDate;

    public PerishableProduct(ProductTypeEnum type, String name, String brand, BigDecimal price, Instant expirationDate) {
        super(type, name, brand, price);
        this.expirationDate = expirationDate;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }
}

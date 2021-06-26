package com.company.products;

import com.company.enums.ProductTypeEnum;

import java.math.BigDecimal;

public abstract class BaseProduct {
    private final ProductTypeEnum type;
    private final String name;
    private final String brand;
    private final BigDecimal price;

    public BaseProduct(ProductTypeEnum type, String name, String brand, BigDecimal price) {
        this.type = type;
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public ProductTypeEnum getType() {
        return this.type;
    }

    public BigDecimal getPrice() {
        return this.price;
    }
}

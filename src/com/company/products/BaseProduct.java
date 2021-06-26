package com.company.products;

import com.company.enums.ProductTypeEnum;

import java.math.BigDecimal;

public abstract class BaseProduct {
    private ProductTypeEnum type;
    private String name;
    private String brand;
    private BigDecimal price;

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

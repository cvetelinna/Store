package com.company.products;

import com.company.enums.ClothingSizeEnum;
import com.company.enums.ProductTypeEnum;
import com.company.utils.Constants;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;

public class Clothing extends BaseProduct{
    private ClothingSizeEnum size;
    private String color;

    public Clothing(ProductTypeEnum type, String name, String brand, BigDecimal price, ClothingSizeEnum size, String color) {
        super(type, name, brand, price);
        this.size = size;
        this.color = color;
    }
}

package com.company.products;

import com.company.enums.ProductTypeEnum;
import com.company.utils.Constants;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

public class Appliance extends BaseProduct{
    private final BigDecimal weight;
    private final String model;
    private final Instant productionDate;

    public Appliance(ProductTypeEnum type,
                     String name,
                     String brand,
                     BigDecimal price,
                     BigDecimal weight,
                     String model,
                     Instant productionDate) {
        super(type, name, brand, price);
        this.weight = weight;
        this.model = model;
        this.productionDate = productionDate;
    }
}

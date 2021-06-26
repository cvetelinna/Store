package products;

import enums.ProductTypeEnum;

import java.math.BigDecimal;
import java.time.Instant;

public class Appliance extends BaseProduct {
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

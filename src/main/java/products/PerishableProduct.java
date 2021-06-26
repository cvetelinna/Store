package products;

import enums.ProductTypeEnum;

import java.math.BigDecimal;
import java.time.Instant;

public class PerishableProduct extends BaseProduct {
    private final Instant expirationDate;

    public PerishableProduct(ProductTypeEnum type, String name, String brand, BigDecimal price, Instant expirationDate) {
        super(type, name, brand, price);
        this.expirationDate = expirationDate;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }
}

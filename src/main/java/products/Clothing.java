package products;

import enums.ClothingSizeEnum;
import enums.ProductTypeEnum;

import java.math.BigDecimal;

public class Clothing extends BaseProduct{
    private final ClothingSizeEnum size;
    private final String color;

    public Clothing(ProductTypeEnum type, String name, String brand, BigDecimal price, ClothingSizeEnum size, String color) {
        super(type, name, brand, price);
        this.size = size;
        this.color = color;
    }
}

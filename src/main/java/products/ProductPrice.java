package products;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPrice that = (ProductPrice) o;
        return isDiscounted == that.isDiscounted && originalPrice.equals(that.originalPrice) && discountAmount.equals(that.discountAmount) && discountPercentage.equals(that.discountPercentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalPrice, discountAmount, discountPercentage, isDiscounted);
    }
}

package com.company.services;

import com.company.products.BaseProduct;
import com.company.products.PerishableProduct;
import com.company.products.ProductPrice;
import com.company.utils.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

public class ProductService implements IProductService {
    @Override
    public ProductPrice getPriceWithDiscount(BaseProduct baseProduct) {
        return switch (baseProduct.getType()) {
            case FOOD, BEVERAGE -> getDiscountForPerishable((PerishableProduct) baseProduct);
            case APPLIANCE -> getDiscountForAppliance(baseProduct);
            case CLOTHING -> getDiscountForClothing(baseProduct);
        };
    }

    private ProductPrice getDiscountForPerishable(PerishableProduct perishableProduct) {
        var timeNow = Instant.now();
        var dateForPartialDiscount = perishableProduct.getExpirationDate().minus(Duration.ofDays(5));
        var dateForFullDiscount = perishableProduct.getExpirationDate().minus(Duration.ofDays(1));

        if (timeNow.isAfter(dateForFullDiscount)){
            var discountPercentage = new BigDecimal("1")
                    .subtract(new BigDecimal(Constants.perishableFullDiscount));
            var priceWithDiscount = perishableProduct.getPrice()
                    .multiply(new BigDecimal(Constants.perishableFullDiscount))
                    .setScale(2, RoundingMode.HALF_UP);
            var discountAmount = perishableProduct.getPrice().subtract(priceWithDiscount);

            return new ProductPrice(
                    perishableProduct.getPrice(),
                    discountAmount,
                    discountPercentage,
                    true
            );
        }

        if (timeNow.isAfter(dateForPartialDiscount)) {
            var discountPercentage = new BigDecimal("1")
                    .subtract(new BigDecimal(Constants.perishablePartialDiscount));
            var priceWithDiscount = perishableProduct.getPrice()
                    .multiply(new BigDecimal(Constants.perishablePartialDiscount))
                    .setScale(2, RoundingMode.HALF_UP);
            var discountAmount = perishableProduct.getPrice().subtract(priceWithDiscount);

            return new ProductPrice(
                    perishableProduct.getPrice(),
                    discountAmount,
                    discountPercentage,
                    true
            );
        }

        return new ProductPrice(perishableProduct.getPrice());
    }

    private ProductPrice getDiscountForAppliance(BaseProduct baseProduct){
        var currentWeekDay = Instant.now().atZone(ZoneId.systemDefault()).getDayOfWeek();

        if (baseProduct.getPrice().compareTo(new BigDecimal(Constants.appliancePriceDiscountThreshold)) > 0) {
            switch (currentWeekDay) {
                case SATURDAY, SUNDAY -> {
                    var discountPercentage = new BigDecimal("1")
                                    .subtract(new BigDecimal(Constants.applianceWeekendDiscount));
                    var priceWithDiscount = baseProduct.getPrice()
                                    .multiply(new BigDecimal(Constants.applianceWeekendDiscount))
                                    .setScale(2, RoundingMode.HALF_UP);
                    var discountAmount = baseProduct.getPrice().subtract(priceWithDiscount);

                    return new ProductPrice(
                            baseProduct.getPrice(),
                            discountAmount,
                            discountPercentage,
                            true);
                }
            }
        }

        return new ProductPrice(baseProduct.getPrice());
    }
    private ProductPrice getDiscountForClothing(BaseProduct baseProduct) {
        var currentWeekDay = Instant.now().atZone(ZoneId.systemDefault()).getDayOfWeek();

        switch(currentWeekDay){
            case SATURDAY:
            case SUNDAY:
                return new ProductPrice(baseProduct.getPrice());
            default:
                var discountPercentage = new BigDecimal("1")
                        .subtract(new BigDecimal(Constants.clothingWeekDayDiscount));
                var priceWithDiscount = baseProduct.getPrice()
                        .multiply(new BigDecimal(Constants.clothingWeekDayDiscount))
                        .setScale(2, RoundingMode.HALF_UP);
                var discountAmount = baseProduct.getPrice()
                        .subtract(priceWithDiscount);

                return new ProductPrice(
                        baseProduct.getPrice(),
                        discountAmount,
                        discountPercentage,
                        true);
        }
    }
}

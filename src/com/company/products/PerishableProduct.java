package com.company.products;

import com.company.utils.Constants;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

public class PerishableProduct extends BaseProduct {
    private Instant expirationDate;


    @Override
    public BigDecimal getDiscountedPrice() {

        var timeNow = Instant.now();
        var partialDiscount = expirationDate.minus(Duration.ofDays(5));
        var fullDiscount = expirationDate.minus(Duration.ofDays(1));

        if (timeNow.isAfter(fullDiscount)){
            return getPrice().multiply(new BigDecimal(Constants.perishableFullDiscount));
        }
        if (timeNow.isAfter(partialDiscount)){
            return getPrice().multiply(new BigDecimal(Constants.perishablePartialDiscount));
        }
        return getPrice();
    }
}

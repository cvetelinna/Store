package com.company.products;

import com.company.utils.Constants;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;

public class Appliance extends BaseProduct{
    @Override
    public BigDecimal getDiscountedPrice() {
        var currentWeekDay = Instant.now().atZone(ZoneId.systemDefault()).getDayOfWeek();
        if (getPrice().compareTo(new BigDecimal(Constants.appliancePriceDiscountThreshold)) > 0){
            switch (currentWeekDay){
                case SATURDAY:
                case SUNDAY:
                    return getPrice().multiply(new BigDecimal(Constants.applianceWeekendDiscount));
                default:
                    return getPrice();
            }
        }

        return getPrice();
    }
}

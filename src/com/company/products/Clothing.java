package com.company.products;

import com.company.enums.ClothingSizeEnum;
import com.company.utils.Constants;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;

public class Clothing extends BaseProduct{
    private ClothingSizeEnum size;

    @Override
    public BigDecimal getDiscountedPrice() {
        var currentWeekDay = Instant.now().atZone(ZoneId.systemDefault()).getDayOfWeek();

        switch(currentWeekDay){
            case SATURDAY:
            case SUNDAY:
                return getPrice();
            default:
                return getPrice().multiply(new BigDecimal(Constants.clothingWeekDayDiscount));
        }
    }
}

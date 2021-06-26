package com.company.services;

import com.company.distribution.CartItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;

public class CashierService implements ICashierService {
    private final IProductService productService;
    private final ICartService cartService;

    public CashierService(IProductService productService, ICartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @Override
    public void printReceipt(List<CartItem> items, Instant dateOfPurchase) {
        var printDate = String.format("Date: %s", dateOfPurchase);
        System.out.println(printDate);
        System.out.println("---Products---");
        for (var item : items) {
            var productPrice = productService.getPriceWithDiscount(item.getProduct());
            var s = String.format(
                    "%s - %s\n" +
                    "%.2f x $ %.2f = $%.2f\n",
                    item.getProduct().getName(),
                    item.getProduct().getBrand(),
                    item.getQuantity(),
                    item.getProduct().getPrice(),
                    item.getTotalPrice());

            if (productPrice.isDiscounted()) {
                var discountPercentage = productPrice
                        .getDiscountPercentage()
                        .multiply(new BigDecimal("100"));

                s += String.format(
                        "#discount %s%% -$%s\n",
                        discountPercentage,
                        productPrice.getDiscountAmount()
                                .multiply(item.getQuantity()).setScale(2, RoundingMode.HALF_UP));
            }

            System.out.println(s);
        }

        System.out.println("----------------------------------------");
        System.out.printf("SUBTOTAL: $%s%n",
                cartService.sumSubtotal(items)
                    .setScale(2, RoundingMode.HALF_UP));
        System.out.printf("DISCOUNT: $%s%n",
                cartService.sumTotalDiscountAmount(items)
                    .setScale(2, RoundingMode.HALF_UP));
        System.out.printf("TOTAL: $%s%n",
                cartService.sumTotal(items));
    }
}
/*
apple - Brand A

2.45 x $1.50 = $3.68

#discount 50%  -$1.84


milk BrandM
3 x $0.99 = $2.97


T-shirt BrandT M violet
2 x $15.99 = $31.98
#discount 10% -$3.20


laptop BrandL ModelL
1 x $2345 = $2345
-----------------------------------------------------------------------------------

SUBTOTAL: $2383.63
DISCOUNT: -$5.04

TOTAL: $2378.59

*/
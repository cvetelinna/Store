package services;

import distribution.CartItem;

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
                cartService.sumSubtotal(items));
        System.out.printf("DISCOUNT: $%s%n",
                cartService.sumTotalDiscountAmount(items));
        System.out.printf("TOTAL: $%s%n",
                cartService.sumTotal(items));
    }
}

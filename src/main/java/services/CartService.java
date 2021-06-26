package services;

import distribution.CartItem;
import utils.Pair;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CartService implements ICartService {
    private final IProductService productService;

    public CartService(IProductService productService) {
        this.productService = productService;
    }

    @Override
    public BigDecimal sumTotalDiscountAmount(List<CartItem> items) {
        return items.stream()
                .map(i -> new Pair<>(productService.getPriceWithDiscount(i.getProduct()), i.getQuantity()))
                .map(p -> p.getFirst().getDiscountAmount().multiply(p.getSecond()))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal sumSubtotal(List<CartItem> items) {
        return items.stream()
                .map(i -> new Pair<>(productService.getPriceWithDiscount(i.getProduct()), i.getQuantity()))
                .map(p -> p.getFirst().getOriginalPrice().multiply(p.getSecond()))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal sumTotal(List<CartItem> items) {
        var subtotal = this.sumSubtotal(items);
        var totalDiscountAmount = this.sumTotalDiscountAmount(items);

        return subtotal.subtract(totalDiscountAmount)
                .setScale(2, RoundingMode.HALF_UP);
    }
}

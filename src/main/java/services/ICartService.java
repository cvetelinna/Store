package services;

import distribution.CartItem;

import java.math.BigDecimal;
import java.util.List;

public interface ICartService {
    BigDecimal sumTotalDiscountAmount(List<CartItem> items);

    BigDecimal sumSubtotal(List<CartItem> items);

    BigDecimal sumTotal(List<CartItem> items);
}

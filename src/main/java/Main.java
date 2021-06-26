import distribution.CartItem;
import enums.ClothingSizeEnum;
import enums.ProductTypeEnum;
import products.Appliance;
import products.Clothing;
import products.PerishableProduct;
import services.CartService;
import services.CashierService;
import services.ProductService;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        var apple = new PerishableProduct(
                ProductTypeEnum.FOOD,
                "apple",
                "BrandA",
                new BigDecimal("1.5"),
                LocalDate
                        .of(2021, 6, 14)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant());

        var milk = new PerishableProduct(
                ProductTypeEnum.FOOD,
                "milk",
                "BrandB",
                new BigDecimal("0.99"),
                LocalDate
                        .of(2022, 2, 2)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant());

        var clothing = new Clothing(
                ProductTypeEnum.CLOTHING,
                "t-shirt",
                "BrandT",
                new BigDecimal("15.99"),
                ClothingSizeEnum.S,
                "violet");

        var appliance = new Appliance(
                ProductTypeEnum.APPLIANCE,
                "laptop",
                "apple",
                new BigDecimal("2345"),
                new BigDecimal("1.5"),
                "ModelL",
                LocalDate
                        .of(2020, 12, 2)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant());

        var cartItems = new ArrayList<CartItem>();
        cartItems.add(new CartItem(apple, new BigDecimal("2.45")));
        cartItems.add(new CartItem(milk, new BigDecimal("3")));
        cartItems.add(new CartItem(appliance,new BigDecimal("2")));
        cartItems.add(new CartItem(clothing, new BigDecimal("2")));

         var productService = new ProductService();
         var cartService = new CartService(productService);
         var cashierService = new CashierService(productService, cartService);

         cashierService.printReceipt(cartItems, Instant.now());
    }
}

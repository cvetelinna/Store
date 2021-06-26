package services;

import enums.ClothingSizeEnum;
import enums.ProductTypeEnum;
import org.junit.jupiter.api.Test;
import products.Appliance;
import products.Clothing;
import products.PerishableProduct;
import products.ProductPrice;

import java.math.BigDecimal;
import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    void perishableProductWithLessThan1DayToExpirationShouldHave50PercentDiscount() {
        var productService = new ProductService();
        var perishableProduct = new PerishableProduct(
                ProductTypeEnum.FOOD,
                "Apple",
                "Apple Brand",
                new BigDecimal("1.50"),
                Instant.now().plus(Duration.ofHours(12))
        );

        var expectedProductPrice = new ProductPrice(
                new BigDecimal("1.50"),
                new BigDecimal("0.75"),
                new BigDecimal("0.5"),
                true
        );

        var actualProductPrice = productService.getPriceWithDiscount(perishableProduct);

        assertEquals(actualProductPrice, expectedProductPrice);
    }

    @Test
    void perishableProductWithLessThan5DaysToExpirationShouldHave10PercentDiscount() {
        var productService = new ProductService();
        var perishableProduct = new PerishableProduct(
                ProductTypeEnum.BEVERAGE,
                "Milk",
                "Milk Brand",
                new BigDecimal("2.50"),
                Instant.now().plus(Duration.ofDays(4))
        );

        var expectedProductPrice = new ProductPrice(
                new BigDecimal("2.50"),
                new BigDecimal("0.25"),
                new BigDecimal("0.1"),
                true
        );

        var actualProductPrice = productService.getPriceWithDiscount(perishableProduct);

        assertEquals(actualProductPrice, expectedProductPrice);
    }

    @Test
    void perishableProductWithMoreThan5DaysToExpireShouldHaveNoDiscount() {
        var productService = new ProductService();
        var perishableProduct = new PerishableProduct(
                ProductTypeEnum.FOOD,
                "Strawberries",
                "Bonita",
                new BigDecimal("1.50"),
                Instant.now().plus(Duration.ofDays(6))
        );

        var expectedProductPrice = new ProductPrice(
                new BigDecimal("1.50")
        );

        var actualProductPrice = productService.getPriceWithDiscount(perishableProduct);

        assertEquals(actualProductPrice, expectedProductPrice);
    }

    @Test
    void clothingOnWeekendShouldHaveNoDiscount() {
        var productService = new ProductService();
        var clothing = new Clothing(
                ProductTypeEnum.CLOTHING,
                "t-shirt",
                "BrandT",
                new BigDecimal("15.99"),
                ClothingSizeEnum.S,
                "violet");

        var expectedProductPrice = new ProductPrice(
                clothing.getPrice()
        );

        var actualProductPrice = productService.getDiscountForClothing(clothing, DayOfWeek.SATURDAY);

        assertEquals(actualProductPrice, expectedProductPrice);
    }

    @Test
    void clothingOnWorkingDayShouldHave10PercentDiscount() {
        var productService = new ProductService();

        var clothing = new Clothing(
                ProductTypeEnum.CLOTHING,
                "blouse",
                "adiBas",
                new BigDecimal("10"),
                ClothingSizeEnum.M,
                "Blue");

        var expectedProductPrice = new ProductPrice(
                new BigDecimal("10"),
                new BigDecimal("1.00"),
                new BigDecimal("0.1"),
                true
        );

        var actualProductPrice = productService.getDiscountForClothing(clothing, DayOfWeek.WEDNESDAY);

        assertEquals(expectedProductPrice, actualProductPrice);
    }

    @Test
    void applianceOnWeekendAndCostsOver999ShouldHave5PercentDiscount() {
        var productService = new ProductService();

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

        var expectedProductPrice = new ProductPrice(
                new BigDecimal("2345"),
                new BigDecimal("117.25"),
                new BigDecimal("0.05"),
                true
        );

        var actualProductPrice = productService.getDiscountForAppliance(appliance, DayOfWeek.SUNDAY);

        assertEquals(expectedProductPrice, actualProductPrice);
    }

    @Test
    void applianceOnWeekDayAndCostsOver999ShouldHaveNoDiscount() {
        var productService = new ProductService();

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

        var expectedProductPrice = new ProductPrice(
                new BigDecimal("2345")
        );

        var actualProductPrice = productService.getDiscountForAppliance(appliance, DayOfWeek.MONDAY);

        assertEquals(expectedProductPrice, actualProductPrice);
    }

    @Test
    void applianceOnWeekendAndDoesNotCostOver999ShouldHaveNoDiscount() {
        var productService = new ProductService();

        var appliance = new Appliance(
                ProductTypeEnum.APPLIANCE,
                "laptop",
                "apple",
                new BigDecimal("998"),
                new BigDecimal("1.5"),
                "ModelL",
                LocalDate
                        .of(2020, 12, 2)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant());

        var expectedProductPrice = new ProductPrice(
                new BigDecimal("998")
        );

        var actualProductPrice = productService.getDiscountForAppliance(appliance, DayOfWeek.SUNDAY);

        assertEquals(expectedProductPrice, actualProductPrice);
    }

    @Test
    void applianceOnWeekDayAndDoesNotCostOver999ShouldHaveNoDiscount() {
        var productService = new ProductService();

        var appliance = new Appliance(
                ProductTypeEnum.APPLIANCE,
                "laptop",
                "apple",
                new BigDecimal("998"),
                new BigDecimal("1.5"),
                "ModelL",
                LocalDate
                        .of(2020, 12, 2)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant());

        var expectedProductPrice = new ProductPrice(
                new BigDecimal("998")
        );

        var actualProductPrice = productService.getDiscountForAppliance(appliance, DayOfWeek.MONDAY);

        assertEquals(expectedProductPrice, actualProductPrice);
    }


}
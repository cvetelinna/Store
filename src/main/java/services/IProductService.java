package services;

import products.BaseProduct;
import products.ProductPrice;

public interface IProductService {
    ProductPrice getPriceWithDiscount(BaseProduct baseProduct);
}

package com.company.services;

import com.company.products.BaseProduct;
import com.company.products.ProductPrice;

public interface IProductService {
    ProductPrice getPriceWithDiscount(BaseProduct baseProduct);
}

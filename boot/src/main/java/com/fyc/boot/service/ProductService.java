package com.fyc.boot.service;

import com.fyc.boot.domain.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getProducts();
    Product getProductID(Integer id);
    Product postProduct(Product producto);
    Product putProduct(Product producto);
    Product patchProduct(Product producto);
    Product deleteProduct(Integer id);
}

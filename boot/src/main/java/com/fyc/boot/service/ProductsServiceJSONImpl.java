package com.fyc.boot.service;

import com.fyc.boot.domain.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

//lo convertimos en el preferencial
//@Primary

//@Service("jsonResourcesService")
@Service
@ConditionalOnProperty(name = "service.products", havingValue = "json")
public class ProductsServiceJSONImpl implements ProductService{


    @Override
    public List<Product> getProducts() {
        List<Product> products;

        try {
            products = new ObjectMapper()
                    .readValue(this.getClass().getResourceAsStream("/products.json"),
                            new TypeReference<List<Product>>() {});

            return products;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Product getProductID(Integer id) {
        List<Product> products;
        try {
            products = new ObjectMapper()
                    .readValue(this.getClass().getResourceAsStream("/products.json"),
                            new TypeReference<List<Product>>() {});

            for (Product p : products){
                if(p.getId().equals(id)){
                    return p;
                }
            }

            return null;
        }catch (IOException e) {
            throw new RuntimeException("Error leyendo el JSON", e);
        }
    }

    @Override
    public Product postProduct(Product producto) {
        return null;
    }

    @Override
    public Product putProduct(Product producto) {
        return null;
    }

    @Override
    public Product patchProduct(Product producto) {
        return null;
    }

    @Override
    public Product deleteProduct(Integer id) {
        return null;
    }
}

package com.fyc.boot.service;

import com.fyc.boot.domain.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



//@Service("listResourcesService")
@Service
@ConditionalOnProperty(name = "service.products", havingValue = "list")
public class ProductsServiceImpl implements ProductService {

    List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(123,"Laptop",799.99,10),
            new Product(345,"Smartphone",499.99,25),
            new Product(678,"Tablet",299.99,15),
            new Product(901,"Smartwatch",199.99,30)
    ));


    //Retorno de todos los productos
    @Override
    public List<Product> getProducts(){
        return products;
    }


    //Retorno la lista de productos por id
    @Override
    public Product getProductID(Integer id){
        for (Product p : products){
            if (p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    //Realizando el metodo para agregar(post)
    public Product postProduct(Product producto){
        for (Product p:products){
            if (p.getId().equals(producto.getId())){
                return null;
            }
        }

        products.add(producto);
        System.out.println("El producto a sido agregado de una manera adecuada");
        return producto;
    }


    //Realizar el actualizado total(put)
    public Product putProduct(Product producto){
        for (Product p : products){
            if (p.getId().equals(producto.getId())){
                p.setName(producto.getName());
                p.setPrice(producto.getPrice());
                p.setStock(producto.getStock());
                System.out.println("Se actualizo correctamente el producto " + producto.getName());
                return p;
            }
        }
        return null;
    }

    //Realizar el actualizado parcial(patch)
    public Product patchProduct(Product producto){
        for (Product p : products){
            if (p.getId().equals(producto.getId())){
                if (producto.getName() != null){
                    p.setName(producto.getName());
                }

                if (producto.getPrice() != null){
                    p.setPrice(producto.getPrice());
                }

                if (producto.getStock() != null){
                    p.setStock(producto.getStock());
                }

                System.out.println("Se actualizo correctamente el producto " + producto.getName());
                return p;
            }
        }
        return  null;
    }

    //Realizamos el eliminado de un producto
    public Product deleteProduct(Integer id){
        for (Product p : products){
            if (p.getId().equals(id)){
                products.remove(p);
                System.out.println("El producto a sido eliminado exitosamente");
                return p;
            }
        }
        return null;
    }

}

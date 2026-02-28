package com.fyc.boot.controller;

import com.fyc.boot.configurations.ExternalizedConfigurations;
import com.fyc.boot.domain.Product;
import com.fyc.boot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/productos")
public class ProductController {

    //ProductService ps = new ProductsServiceImpl();
    //inyeccion de dependencias
    @Autowired
    //@Qualifier("listResourcesService")
    private ProductService ps;


    //inyeccion de dependencia de configuracion
    @Autowired
    private ExternalizedConfigurations externalizedConfigurations;

    //Traemos lor productos que tenemos en la lista
    @GetMapping
    public ResponseEntity<?> getAllProducts(){

        System.out.println(externalizedConfigurations.toString());
        List<Product> products = ps.getProducts();

        return ResponseEntity.ok(products);
    }

    //Traemos el producto por el ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductsByID(@PathVariable Integer id){
        Product products = ps.getProductID(id);
        if (products != null){
            return ResponseEntity.ok(products);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto con el ID("+ id +") no ha sido encontrado.");
    }

    //Creamos un nuevo producto
    @PostMapping
    public ResponseEntity<?> postProduct(@RequestBody Product producto){

        Product products = ps.postProduct(producto);

        if (products == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ya existe un producto con ese ID.");
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(products.getName())
                .toUri();
        return ResponseEntity.created(location).body(products);


    }


    //Actualizamos un producto en su totalidad
    @PutMapping
    public ResponseEntity<?> putProductTotal(@RequestBody Product producto){
        Product products = ps.putProduct(producto);

        if (products != null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el produto a actualizar");
    }


    //Actualizamos un producto parcialmente
    @PatchMapping
    public ResponseEntity<?> patchProductParcial(@RequestBody Product producto){
        Product products = ps.patchProduct(producto);

        if (products != null){
            return ResponseEntity.ok("El producto con ID: "+producto.getId()+" fue actualizado correctamente.");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el produto con ID: "+producto.getId()+" para ser actualizado.");
    }


    //Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        Product products = ps.deleteProduct(id);

        if (products != null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el produto con ID: "+id+" para ser eliminado.");
    }

}
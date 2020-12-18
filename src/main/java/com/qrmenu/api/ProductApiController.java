package com.qrmenu.api;

import com.qrmenu.data.ProductRepository;
import com.qrmenu.domain.Product;
import com.qrmenu.service.Message;
import com.qrmenu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/product",
        produces="application/json")
public class ProductApiController {

    @Autowired
    private ProductService productService;

    @PutMapping(consumes = "application/json", value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Message> postProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping(consumes = "application/json", value = "/update")
    public ResponseEntity<Message> updateProduct(@RequestBody Product update){
        return productService.updateProduct(update.getId(), update);
    }

    @GetMapping(produces = "application/json")
    public Iterable<Product> allProducts(){return productService.allProducts();}

    @DeleteMapping(consumes = "application/json", value = "/delete")
    public ResponseEntity<Message> deleteProduct(@RequestBody Product product){
        return productService.deleteProductById(product.getId());
    }



}

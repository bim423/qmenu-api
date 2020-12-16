package com.qrmenu.api;

import com.qrmenu.data.ProductRepository;
import com.qrmenu.domain.Product;
import com.qrmenu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/product",
        produces="application/json")
public class ProductApiController {

    @Autowired
    private ProductService productService;

    @PostMapping(consumes = "application/json", value = "/{submenuId}/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Product postProduct(@PathVariable(value = "submenuId") Integer submenuId,
                               @RequestBody Product product){
        return productService.addProduct(submenuId, product);
    }

    @PutMapping(consumes = "application/json")
    public Product updateProduct(@RequestBody Product update){
        return productService.updateProduct(update.getId(), update);
    }

    @GetMapping(produces = "application/json")
    public Iterable<Product> allProducts(){return productService.allProducts();}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Integer productId){
        productService.deleteProductById(productId);
    }



}

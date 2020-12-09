package com.qrmenu.api;

import com.qrmenu.data.ProductRepository;
import com.qrmenu.domain.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/product",
        produces="application/json")
public class ProductApiController {

    private ProductRepository repo;

    public ProductApiController(ProductRepository repo){this.repo = repo;}

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Product postProduct(@RequestBody Product product){return repo.save(product);}

    @PutMapping(consumes = "application/json")
    public Product putProduct(@RequestBody Product product){return repo.save(product);}

    @GetMapping(produces = "application/json")
    public Iterable<Product> allProducts(){return repo.findAll();}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Integer productId){
        try {
            repo.deleteById(productId);
        }catch (EmptyResultDataAccessException e){

        }
    }



}

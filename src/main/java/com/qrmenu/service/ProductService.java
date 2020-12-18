package com.qrmenu.service;

import com.qrmenu.data.ProductRepository;
import com.qrmenu.data.SubMenuRepository;
import com.qrmenu.domain.Product;
import com.qrmenu.domain.SubMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SubMenuRepository subMenuRepository;

    public Iterable<Product> allProducts(){
        return productRepository.findAll();
    }

    public ResponseEntity<Message> addProduct(Product product){

        Optional<SubMenu> subMenuOptional = subMenuRepository.findById(product.getSubId());
        if (!subMenuOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("Submenu is not valid", 0));
        }

        SubMenu subMenu = subMenuOptional.get();
        product.setSubMenu(subMenu);
        subMenu.getProducts().add(product);

        subMenuRepository.save(subMenu);
        productRepository.save(product);

        return ResponseEntity.status(HttpStatus.OK).body(new Message("Product added successfully", product.getId()));
    }

    public ResponseEntity<Message> deleteProductById(Integer productId){

        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()){
            product.get().getSubMenu().getProducts().remove(product.get());
            subMenuRepository.save(product.get().getSubMenu());
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("Product is not available", productId));
        }

        try {
            productRepository.deleteById(productId);
        }catch (EmptyResultDataAccessException e){

        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Message("Product deleted successfully", productId));
    }

    public ResponseEntity<Message> updateProduct(Integer productId, Product update){

        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (!optionalProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("Product is not valid", 0));
        }

        Product existingProduct = optionalProduct.get();

        if (update.getName() == null || update.getDescription() == null || update.getPrice() == 0.0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("There are empty fields", productId));
        }

        existingProduct.setName(update.getName());
        existingProduct.setDescription(update.getDescription());
        existingProduct.setPrice(update.getPrice());


        return ResponseEntity.status(HttpStatus.OK)
                .body(new Message("Product updated successfully", productId));
    }

}

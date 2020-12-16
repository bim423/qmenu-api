package com.qrmenu.service;

import com.qrmenu.data.ProductRepository;
import com.qrmenu.data.SubMenuRepository;
import com.qrmenu.domain.Product;
import com.qrmenu.domain.SubMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
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

    public Product addProduct(Integer submenuId, Product product){

        Optional<SubMenu> subMenuOptional = subMenuRepository.findById(submenuId);
        if (!subMenuOptional.isPresent()){
            throw new EntityNotFoundException();
        }

        SubMenu subMenu = subMenuOptional.get();
        product.setSubMenu(subMenu);
        subMenu.getProducts().add(product);

        subMenuRepository.save(subMenu);
        productRepository.save(product);

        return product;
    }

    public void deleteProductById(Integer productId){

        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()){
            product.get().getSubMenu().getProducts().remove(product.get());
            subMenuRepository.save(product.get().getSubMenu());
        }

        try {
            productRepository.deleteById(productId);
        }catch (EmptyResultDataAccessException e){

        }
    }

    public Product updateProduct(Integer productId, Product update){

        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (!optionalProduct.isPresent()){
            return null;
        }

        Product existingProduct = optionalProduct.get();

        if (update.getName() != null){
            existingProduct.setName(update.getName());
        }
        if (update.getDescription() != null){
            existingProduct.setDescription(update.getDescription());
        }
        if (update.getPrice() != 0.0){
            existingProduct.setPrice(update.getPrice());
        }

        return productRepository.save(existingProduct);
    }

}

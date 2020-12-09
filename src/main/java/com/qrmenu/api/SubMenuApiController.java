package com.qrmenu.api;

import com.qrmenu.data.ProductRepository;
import com.qrmenu.data.SubMenuRepository;
import com.qrmenu.domain.Product;
import com.qrmenu.domain.SubMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/submenu", produces = "application/json")
public class SubMenuApiController {

    private SubMenuRepository repo;
    private ProductRepository productRepo;

    @Autowired
    public SubMenuApiController(SubMenuRepository repo, ProductRepository productRepo){
        this.repo=repo;
        this.productRepo=productRepo;
    }

    @GetMapping(produces = "application/json")
    public Iterable<SubMenu> allSubMenu(){return repo.findAll();}


    @PutMapping(consumes = "application/json")
    public SubMenu putSubMenu(@RequestBody SubMenu subMenu){
        return repo.save(subMenu);
    }

    @DeleteMapping("/{id}")
    public void deleteSubmenu(@PathVariable("id") Integer submenuId){
        try {
            repo.deleteById(submenuId);
        }catch (EmptyResultDataAccessException e){

        }
    }


}

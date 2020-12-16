package com.qrmenu.api;

import com.qrmenu.data.ProductRepository;
import com.qrmenu.data.SubMenuRepository;
import com.qrmenu.domain.Product;
import com.qrmenu.domain.SubMenu;
import com.qrmenu.service.SubMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/submenu", produces = "application/json")
public class SubMenuApiController {

    @Autowired
    SubMenuService subMenuService;


    @GetMapping(produces = "application/json")
    public Iterable<SubMenu> allSubMenu(){return subMenuService.allSubMenus();}


    @PutMapping(consumes = "application/json")
    public SubMenu putSubMenu(@RequestBody SubMenu subMenu){
        return subMenuService.addSubMenu(subMenu);
    }

    @DeleteMapping("/{id}")
    public void deleteSubmenu(@PathVariable("id") Integer submenuId){
       subMenuService.deleteSubMenuById(submenuId);
    }


}

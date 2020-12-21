package com.qrmenu.api;

import com.qrmenu.domain.SubMenu;
import com.qrmenu.service.Message;
import com.qrmenu.service.SubMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/submenu", produces = "application/json")
public class SubMenuApiController {

    @Autowired
    SubMenuService subMenuService;


    @GetMapping(produces = "application/json")
    public Iterable<SubMenu> allSubMenu(){return subMenuService.allSubMenus();}


    @PutMapping(consumes = "application/json", path = "/create")
    public ResponseEntity<Message> putSubMenu(@RequestBody SubMenu subMenu){
        return subMenuService.addSubMenu(subMenu);
    }

    @PutMapping(consumes = "application/json", path = "/update")
    public ResponseEntity<Message> updateSubMenu(@RequestBody SubMenu subMenu){
        return subMenuService.update(subMenu);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteSubmenu(@PathVariable("id") Integer submenuId){
       return subMenuService.deleteSubMenuById(submenuId);
    }


}

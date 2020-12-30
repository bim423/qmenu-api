package com.qrmenu.api;

import com.qrmenu.data.SubMenuRepository;
import com.qrmenu.domain.Menu;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/menu")
@CrossOrigin("*")
public class MenuApiController {

        private SubMenuRepository repo;

        public MenuApiController(SubMenuRepository repo){this.repo = repo;}

        @GetMapping(produces = "application/json")
        public Menu allMenus(){
                Menu menu = new Menu(repo.findAll());
                return menu;
        }


}

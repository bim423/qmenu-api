package com.qrmenu.api;

import com.qrmenu.data.MenuRepository;
import com.qrmenu.domain.Menu;
import com.qrmenu.domain.Product;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/menu")
public class MenuApiController {

        private MenuRepository repo;

        public MenuApiController(MenuRepository repo){this.repo = repo;}

        @GetMapping(produces = "application/json")
        public Iterable<Menu> allMenus(){return repo.findAll();}

        @PutMapping(path = "/save", consumes = "application/json")
        public Menu putMenu(@RequestBody Menu menu){
            return repo.save(menu);
        }

}

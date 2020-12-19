package com.qrmenu.service;

import com.qrmenu.data.SubMenuRepository;
import com.qrmenu.domain.SubMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SubMenuService {

    @Autowired
    SubMenuRepository subMenuRepository;


    public Iterable<SubMenu> allSubMenus(){
        return subMenuRepository.findAll();
    }

    public ResponseEntity<Message> addSubMenu(SubMenu subMenu){
        subMenuRepository.save(subMenu);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Message("Category created successfully", subMenu.getId()));
    }

    public void deleteSubMenuById(Integer id){
        try {
            subMenuRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){

        }
    }

    public SubMenu update(SubMenu update) {

        SubMenu subMenu = subMenuRepository.findById(update.getId()).get();

        if (update.getDescription() != null){
            subMenu.setDescription(update.getDescription());
        }
        if (update.getCategory() != null){
            subMenu.setCategory(update.getCategory());
        }
        return subMenuRepository.save(subMenu);

    }
}

package com.qrmenu.service;

import com.qrmenu.data.MenuRepository;
import com.qrmenu.data.SubMenuRepository;
import com.qrmenu.domain.Menu;
import com.qrmenu.domain.SubMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubMenuService {

    @Autowired
    SubMenuRepository subMenuRepository;

    @Autowired
    MenuRepository menuRepository;

    public Iterable<SubMenu> allSubMenus(){
        return subMenuRepository.findAll();
    }

    public SubMenu addSubMenu(SubMenu subMenu){
        Optional<Menu> optional = menuRepository.findById(1);
        if (!optional.isPresent()){
            Menu menu = new Menu(1, new ArrayList<SubMenu>());
            menu.getMenu().add(subMenu);
            menuRepository.save(menu);
            return subMenu;
        }
        optional.get().getMenu().add(subMenu);
        menuRepository.save(optional.get());
        return subMenu;
    }

    public void deleteSubMenuById(Integer id){
        Optional<Menu> optional = menuRepository.findById(1);
        if (!optional.isPresent()){
            try {
                subMenuRepository.deleteById(id);
            }catch (EmptyResultDataAccessException e){

            }
            return;
        }

        optional.get().getMenu().remove(subMenuRepository.findById(id).get());
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

package com.qrmenu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int menuId;

    public Menu(int menuId, List<SubMenu> menu) {
        this.menuId = menuId;
        this.menu = menu;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<SubMenu> menu;

    @JsonIgnore
    public int getMenuId() {
        return menuId;
    }

}

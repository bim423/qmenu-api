package com.qrmenu.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.Table;
import java.util.List;

@Data
@RequiredArgsConstructor
@CrossOrigin
public class Menu {


    public Menu(Iterable<SubMenu> menu) {
        this.menu = menu;
    }

    private Iterable<SubMenu> menu;


}

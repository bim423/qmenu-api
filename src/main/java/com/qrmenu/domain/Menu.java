package com.qrmenu.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class Menu {

    @Id
    private int id;

    private String name;

    @OneToMany
    private List<SubMenu> categories;
}

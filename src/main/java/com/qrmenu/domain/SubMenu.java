package com.qrmenu.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "submenu")
public class SubMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submenu_id")
    private int id;

    @Column(name = "submenu_label")
    private String category;

    @Column(name = "submenu_description")
    private String description;

    @OneToMany(mappedBy = "subMenu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;
}

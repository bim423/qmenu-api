package com.qrmenu.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class SubMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String category;
    private String description;

    @OneToMany(mappedBy = "subMenu", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Product> products;
}

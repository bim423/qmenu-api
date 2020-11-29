package com.qrmenu.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class SubMenu {

    @Id
    private int id;
    private String name;
    private String description;

    @OneToMany(targetEntity = Product.class)
    private List<Product> products;
}

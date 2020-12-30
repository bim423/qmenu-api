package com.qrmenu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_description")
    private String description;

    private double price;

    @Transient
    @JsonInclude
    private int subId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "submenu_id")
    private SubMenu subMenu;

    @JsonIgnore
    public SubMenu getSubMenu(){
        return subMenu;
    }

    @JsonIgnore
    public void setSubMenu(SubMenu subMenu){
        this.subMenu = subMenu;
    }

}

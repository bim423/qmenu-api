package com.qrmenu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String description;

    private double price;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
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

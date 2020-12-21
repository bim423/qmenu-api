package com.qrmenu.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class Desk {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String label;

    private String code;

    @OneToMany(targetEntity = Order.class, mappedBy = "desk")
    private List<Order> orders;


}

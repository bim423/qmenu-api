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
    private String id;

    private String label;

    private String code;

    @OneToMany(targetEntity = Order.class)
    private List<Order> orders;


}

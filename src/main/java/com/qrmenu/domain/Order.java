package com.qrmenu.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int quantity;
    //Type is integer for now
    private int arrivalTime;

    private State state;

    @ManyToMany(targetEntity = Product.class)
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "desk_id")
    private Desk desk;

    private static enum State{
        READY, PREPARING, ERROR
    }
}

package com.qrmenu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_detail_id")
    public int id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id", nullable = false)
    private Order order;

    @Column(name = "quantity")
    private int quantity;

    @JsonIgnore
    public int getId() {
        return id;
    }

    @JsonIgnore
    public Order getOrder() {
        return order;
    }
    @JsonIgnore
    public void setOrder(Order order) {
        this.order = order;
    }
}

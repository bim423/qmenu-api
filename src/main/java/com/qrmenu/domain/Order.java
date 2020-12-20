package com.qrmenu.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDateTime arrivalTime;

    private int state;

    @OneToMany(targetEntity = OrderDetail.class, cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetail> orderedProducts;

    @ManyToOne
    @JoinColumn(name = "desk_id")
    private Desk desk;

    @PrePersist
    public void placedAt() {
        this.arrivalTime = LocalDateTime.now();
    }
}

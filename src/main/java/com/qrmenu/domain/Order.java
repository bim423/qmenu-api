package com.qrmenu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "state")
    private int state;

    @Transient
    private int deskId;

    @OneToMany(targetEntity = OrderDetail.class, cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetail> orderedProducts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desk_id")
    private Desk desk;

    public String getDeskLabel(){
        return desk.getLabel();
    }

    @PrePersist
    public void placedAt() {
        this.arrivalTime = LocalDateTime.now();
    }

    public int getDeskId() {
        return desk.getId();
    }




    @JsonIgnore
    public int getDeskIdPlace(){
        return deskId;
    }

    @JsonIgnore
    public Desk getDesk() {
        return desk;
    }
    @JsonIgnore
    public void setDesk(Desk desk) {
        this.desk = desk;
    }
}

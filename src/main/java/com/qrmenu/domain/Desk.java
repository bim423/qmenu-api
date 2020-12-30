package com.qrmenu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "desk")
public class Desk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "desk_id")
    private int id;

    @Column(name = "label")
    private String label;

    @Column(name = "code")
    private String code;

    @OneToMany(targetEntity = Order.class, mappedBy = "desk")
    private List<Order> orders;

    @PrePersist
    public void deskCode()
            throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(LocalDateTime.now().toString().getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        this.code = DatatypeConverter.printHexBinary(digest).toLowerCase();
    }

    @JsonIgnore
    public List<Order> getOrders() {
        return orders;
    }

    @JsonIgnore
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}

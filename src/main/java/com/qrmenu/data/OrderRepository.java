package com.qrmenu.data;

import com.qrmenu.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    Iterable<Order> findAllByOrderByIdDesc();
}

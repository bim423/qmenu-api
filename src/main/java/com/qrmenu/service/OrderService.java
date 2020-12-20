package com.qrmenu.service;

import com.qrmenu.data.OrderDetailRepository;
import com.qrmenu.data.OrderRepository;
import com.qrmenu.domain.Order;
import com.qrmenu.domain.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository detailRepository;

    public Iterable<Order> allOrders(){return orderRepository.findAll();}

    public ResponseEntity<Message> placeOrder(Order order){

        for (OrderDetail orderDetail: order.getOrderedProducts()) {
            orderDetail.setOrder(order);
            detailRepository.save(orderDetail);
        }
        orderRepository.save(order);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Message("Order is created successfully", order.getId()));
    }
}

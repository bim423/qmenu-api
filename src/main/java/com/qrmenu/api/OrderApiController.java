package com.qrmenu.api;

import com.qrmenu.domain.Order;
import com.qrmenu.service.Message;
import com.qrmenu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/order")
@CrossOrigin("*")
public class OrderApiController {

    @Autowired
    private OrderService orderService;

    @GetMapping(produces = "application/json")
    public Iterable<Order> allOrders(){
        return orderService.allOrders();
    }

    @PutMapping(consumes = "application/json", path = "/place")
    public ResponseEntity<Message> placeOrder(@RequestBody Order order){
        return orderService.placeOrder(order);
    }

    @PutMapping(consumes = "application/json", path = "/update")
    public ResponseEntity<Message> updateOrder(@RequestBody Order order){
        return null;
    }

    @PutMapping(consumes = "application/json", path = "/state")
    public ResponseEntity<Message> updateStatus(@RequestBody Order order){
        return orderService.updateState(order);

    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Message> deleteOrder(@PathVariable("id") Integer orderId){
        return orderService.deleteOrder(orderId);
    }





}

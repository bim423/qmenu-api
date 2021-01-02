package com.qrmenu.service;

import com.qrmenu.data.DeskRepository;
import com.qrmenu.data.OrderDetailRepository;
import com.qrmenu.data.OrderRepository;
import com.qrmenu.data.ProductRepository;
import com.qrmenu.domain.Desk;
import com.qrmenu.domain.Order;
import com.qrmenu.domain.OrderDetail;
import com.qrmenu.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository detailRepository;

    @Autowired
    DeskRepository deskRepository;

    @Autowired
    ProductRepository productRepository;

    public Iterable<Order> allOrders(){return orderRepository.findAll();}

    public ResponseEntity<Message> placeOrder(Order order){

        Optional<Desk> optionalDesk = deskRepository.findById(order.getDeskIdPlace());
        if (!optionalDesk.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("Desk ID is not valid", order.getDeskId()));
        }
        Desk desk = optionalDesk.get();
        desk.getOrders().add(order);
        order.setDesk(desk);

        deskRepository.save(desk);


        for (OrderDetail orderDetail: order.getOrderedProducts()) {
            orderDetail.setOrder(order);
            Optional<Product> optional = productRepository.findById(orderDetail.getProductIdPlace());
            if (optional.isPresent()){
                orderDetail.setProduct(optional.get());
            }
        }

        orderRepository.save(order);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Message("Order is created successfully", order.getId()));
    }

    public ResponseEntity<Message> updateState(Order order){
        Optional<Order> optionalOrder = orderRepository.findById(order.getId());

        if (!optionalOrder.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("Order can not be found", order.getId()));
        }
        if (order.getState() > 2){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("State code is not valid", order.getId()));
        }

        optionalOrder.get().setState(order.getState());

        orderRepository.save(optionalOrder.get());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Message("State is updated successfully", order.getId()));
    }

    public ResponseEntity<Message> deleteOrder(Integer orderId) {

        Optional<Order> optional = orderRepository.findById(orderId);

        if (!optional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("Order ID is not valid", orderId));
        }
        try {
            orderRepository.deleteById(orderId);
        }catch (EmptyResultDataAccessException e){

        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Message("Order deleted successfully", orderId));
    }
}

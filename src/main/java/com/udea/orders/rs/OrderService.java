package com.udea.orders.rs;

import com.udea.orders.dto.Order;
import com.udea.orders.service.OrderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderService {

    @Autowired
    OrderServiceFacade orderServiceFacade;

    @PostMapping(value = "order")
    public Order createOrder(@RequestBody Order order) {
        return orderServiceFacade.createOrder(order);
    }

    @GetMapping("/order/{id}")
    public Order getOrder(@PathVariable String id) {
        Order order = new Order();
        order.setId(id);
        return orderServiceFacade.getOrder(order);
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderServiceFacade.getOrders();
    }

    @PutMapping("/order/{id}")
    public Order updateOrder(
            @PathVariable String id,
            @RequestBody Order order) {
        order.setId(id);
        return orderServiceFacade.updateOrder(order);
    }

    @DeleteMapping("/order/{id}")
    public Order deleteOrder(@PathVariable String id) {
        Order order = new Order();
        order.setId(id);
        return orderServiceFacade.deleteOrder(order);
    }

}

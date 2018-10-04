package com.udea.orders.bo.impl;

import com.udea.orders.bo.EventPublisher;
import com.udea.orders.bo.OrdersBO;
import com.udea.orders.dto.Order;
import com.udea.orders.dto.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class OrdersBOImpl implements OrdersBO {

    @Autowired
    private EventPublisher eventPublisher;

    @Override
    public Order createOrder(Order order) {
        order.setStatus(OrderStatus.CREATED);
        eventPublisher.publishEvent(order);
        return order;
    }

    @Override
    public Order updateOrder(Order order) {
        order.setStatus(OrderStatus.APRROVED);
        eventPublisher.publishEvent(order);
        return order;
    }

    @Override
    public Order deleteOrder(Order order) {
        order.setStatus(OrderStatus.DELETED);
        eventPublisher.publishEvent(order);
        return order;
    }

    @Override
    public Order getOrder(Order order) {
        return order;
    }

    @Override
    public List<Order> getOrders() {
        return new LinkedList<>();
    }

}

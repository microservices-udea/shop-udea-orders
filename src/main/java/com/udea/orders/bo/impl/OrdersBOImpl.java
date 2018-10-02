package com.udea.orders.bo.impl;

import com.udea.orders.bo.OrdersBO;
import com.udea.orders.dto.Order;
import com.udea.orders.dto.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class OrdersBOImpl implements OrdersBO {
    @Override
    public Order createOrder(Order order) {
        order.setStatus(OrderStatus.PLACED);
        return order;
    }

    @Override
    public Order updateOrder(Order order) {
        order.setStatus(OrderStatus.APRROVED);
        return order;
    }

    @Override
    public Order deleteOrder(Order order) {
        order.setStatus(OrderStatus.DELETED);
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

package com.udea.orders.service.impl;

import com.udea.orders.bo.OrdersBO;
import com.udea.orders.dto.Order;
import com.udea.orders.service.OrderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceFacadeImpl implements OrderServiceFacade {

    @Autowired
    OrdersBO orders;

    @Override
    public Order createOrder(Order order) {
        return orders.createOrder(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orders.updateOrder(order);
    }

    @Override
    public Order deleteOrder(Order order) {
        return orders.deleteOrder(order);
    }

    @Override
    public Order getOrder(Order order) {
        return orders.getOrder(order);
    }
}

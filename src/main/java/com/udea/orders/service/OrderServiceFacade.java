package com.udea.orders.service;

import com.udea.orders.domain.Order;

import java.util.List;

public interface OrderServiceFacade {
    Order createOrder(Order order) throws Exception;
    void reservedOrder(Order order);
    Order updateOrder(Order order);
    Order deleteOrder(Order order);
    Order getOrder(Order order);
    List<Order> getOrders();

}

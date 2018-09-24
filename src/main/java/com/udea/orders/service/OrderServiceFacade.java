package com.udea.orders.service;

import com.udea.orders.dto.Order;

public interface OrderServiceFacade {
    Order createOrder(Order order);
    Order updateOrder(Order order);
    Order deleteOrder(Order order);
    Order getOrder(Order order);
}

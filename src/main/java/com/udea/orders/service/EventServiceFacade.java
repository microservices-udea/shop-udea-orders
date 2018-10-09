package com.udea.orders.service;

import com.udea.orders.domain.Order;

public interface EventServiceFacade {

    void createdOrder(Order order);

    void reservedOrder(Order order);

    void paidOrder(String message);

    void completedOrder(String message);

}

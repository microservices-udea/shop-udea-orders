package com.udea.orders.bo;

import com.udea.orders.dto.Order;

import java.util.List;

public interface OrdersBO {
    Order createOrder(Order order);
    Order updateOrder(Order order);
    Order deleteOrder(Order order);
    Order getOrder(Order order);
    List<Order> getOrders();
}

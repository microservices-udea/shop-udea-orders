package com.udea.orders.bo;

import com.udea.orders.dto.Order;

public interface OrdersBO {
    Order createOrder(Order order);
    Order updateOrder(Order order);
    Order deleteOrder(Order order);
    Order getOrder(Order order);
}

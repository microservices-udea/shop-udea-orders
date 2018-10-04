package com.udea.orders.bo;

import com.udea.orders.dto.Order;

public interface EventPublisher {
    void publishEvent(Order order);
}

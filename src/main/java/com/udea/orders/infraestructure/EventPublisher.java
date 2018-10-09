package com.udea.orders.infraestructure;

import com.udea.orders.domain.Order;

public interface EventPublisher {
    void publishEvent(Order order);
}

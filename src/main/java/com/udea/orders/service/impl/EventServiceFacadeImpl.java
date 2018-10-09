package com.udea.orders.service.impl;

import com.udea.orders.infraestructure.EventPublisher;
import com.udea.orders.enumeration.InventoryStatus;
import com.udea.orders.domain.Order;
import com.udea.orders.enumeration.OrderStatus;
import com.udea.orders.service.EventServiceFacade;
import com.udea.orders.service.OrderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EventServiceFacadeImpl implements EventServiceFacade {

    @Autowired
    private OrderServiceFacade orderService;

    @Autowired
    private EventPublisher eventPublisher;

    @Override
    public void createdOrder(Order order) {
        try {
            Order createdOrder = orderService.createOrder(order);
            eventPublisher.publishEvent(createdOrder);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void reservedOrder(Order order) {
        //Se deserializa el mensaje al objeto Order
        Order reservedOrder = new Order();
        String status = order != null ? order.getEventOrder() : "";

        if (InventoryStatus.RESERVED.name().equalsIgnoreCase(status)) {
            //Se actualiza la orden a reservada
            order.setStatus(OrderStatus.RESERVED);

        } else if (InventoryStatus.REJECTED.name().equalsIgnoreCase(status)) {
            //Se actualiza la orden a rechazada
            order.setStatus(OrderStatus.REJECTED);
        }
        // de lo contrario se devuelve mensaje al topico
        reservedOrder = orderService.reservedOrder(order);
        eventPublisher.publishEvent(reservedOrder);

    }

    @Override
    public void paidOrder(String message) {

    }

    @Override
    public void completedOrder(String message) {

    }
}

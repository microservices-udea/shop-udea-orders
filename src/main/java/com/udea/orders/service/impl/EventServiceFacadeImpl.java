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
            order.setStatus(OrderStatus.REJECTED);
            eventPublisher.publishEvent(order);
        }
    }

    @Override
    public void reservedOrder(Order order) {
    	try {
    		String status = order != null ? order.getEventType() : "";

    		if (InventoryStatus.RESERVED.name().equalsIgnoreCase(status)) {
    			//Se actualiza la orden a reservada
    			order.setStatus(OrderStatus.RESERVED);

    		} else if (InventoryStatus.REJECTED.name().equalsIgnoreCase(status)) {
    			//Se actualiza la orden a rechazada
    			order.setStatus(OrderStatus.REJECTED);
    		}
    		orderService.reservedOrder(order);
    		eventPublisher.publishEvent(order);
    	} catch (Exception e) {
    		order.setStatus(OrderStatus.REJECTED);
    		eventPublisher.publishEvent(order);
    	}
    }

    @Override
    public void paidOrder(String message) {

    }

    @Override
    public void completedOrder(String message) {

    }
}

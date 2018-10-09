package com.udea.orders.adapters.impl;

import com.udea.orders.dto.events.catalog.InventoryReservedEvent;
import com.udea.orders.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class InventoryEventAdaptee {

    /**
     * Metodo que convierte el InventoryReservedEvent en un objeto de tipo Order
     *
     * @param event InventoryReservedEvent
     * @return Order
     */
    public Order inventoryReserved(InventoryReservedEvent event) {
        Order order = new Order();
        order.setId(event.getOrderId());
        order.setUserId(event.getUserId());
        order.setEventType(event.getEventType());
        return order;
    }

}

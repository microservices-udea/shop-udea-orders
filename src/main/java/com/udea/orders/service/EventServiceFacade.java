package com.udea.orders.service;

import com.udea.orders.dto.Order;

public interface EventServiceFacade {
    /**
     * Creacion de orden a partir de un mensaje en formato JSON obtenido
     * del topico de checkout de carrito de compras bajo el evento de checkedout
     * @param message Evento para crear orden
     * @return Order
     */
    Order createdOrder(Order order);
    void reservedOrder(Order order);
    void paidOrder(String message);
    void completedOrder(String message);

}

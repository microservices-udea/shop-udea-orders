package com.udea.orders.service;

import com.udea.orders.dto.Order;

import java.io.IOException;
import java.util.List;

public interface OrderServiceFacade {
    Order createOrder(Order order);
    Order updateOrder(Order order);
    Order deleteOrder(Order order);
    Order getOrder(Order order);
    List<Order> getOrders();
    /**
     * Creacion de orden a partir de un mensaje en formato JSON obtenido
     * del topico de checkout de carrito de compras bajo el evento de checkedout
     * @param messageTopic
     * @return Orden , con la informacion necesaria para publicar el evento OrdenCreada
     */
     Order createOrderTopic(String  messageTopic) throws IOException ;
}

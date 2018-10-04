package com.udea.orders.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udea.orders.dto.Order;
import com.udea.orders.dto.OrderStatus;
import com.udea.orders.service.EventServiceFacade;
import com.udea.orders.service.OrderServiceFacade;
import com.udea.orders.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class EventServiceFacadeImpl implements EventServiceFacade {

    @Autowired
    private OrderServiceFacade orderService;

    @Override
    public Order createdOrder(String messageTopic) {
        //Se deserializa el mensaje al objeto Order
        ObjectMapper objectMapper = new ObjectMapper();
        Order order;
        try {
            order = objectMapper.readValue(messageTopic, Order.class);
            order.setShipDate(Utilities.convertDateToString( new Date()));
            //Se crea la orden
            order = orderService.createOrder(order);

        } catch (IOException e) {
            order = new Order();
            order.setStatus(OrderStatus.REJECTED);
        }
        return order;
    }

    @Override
    public void reservedOrder(String message) {

    }

    @Override
    public void paidOrder(String message) {

    }

    @Override
    public void completedOrder(String message) {

    }
}

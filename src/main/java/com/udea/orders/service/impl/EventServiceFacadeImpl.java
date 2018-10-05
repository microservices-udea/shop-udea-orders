package com.udea.orders.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udea.orders.dto.InventoryStatus;
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
    public Order reservedOrder(String message) {
    	//Se deserializa el mensaje al objeto Order
    	ObjectMapper objectMapper = new ObjectMapper();
    	Order order;
    	try {
    		order = objectMapper.readValue(message, Order.class);
    		String status= order!=null? order.getEventOrder():"";

    		if(InventoryStatus.RESERVED.name().equalsIgnoreCase(status)){

    			//Se actualiza la orden a reservada
    			order.setStatus(OrderStatus.RESERVED);
    			order = orderService.updateOrder(order);
    			
    		}else if(InventoryStatus.REJECTED.name().equalsIgnoreCase(status)){

    			//Se actualiza la orden a rechazada
    			order.setStatus(OrderStatus.REJECTED);
    			order = orderService.updateOrder(order);
    		}
    		// de lo contrario se devuelve mensaje al topico
    	} catch (IOException e) {
    		order = new Order();
    		order.setStatus(OrderStatus.REJECTED);
    	}
    	return order;
    }

    @Override
    public void paidOrder(String message) {

    }

    @Override
    public void completedOrder(String message) {

    }
}

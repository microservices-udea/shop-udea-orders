package com.udea.orders.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udea.orders.bo.OrdersBO;
import com.udea.orders.dto.Order;
import com.udea.orders.dto.Product;
import com.udea.orders.service.OrderServiceFacade;
import com.udeaa.orders.util.Utilities;

@Service
public class OrderServiceFacadeImpl implements OrderServiceFacade {

    @Autowired
    private OrdersBO orders;

    @Override
    public Order createOrder(Order order) {
        return orders.createOrder(order);
    }

    @Override
    public Order createOrderTopic(String  messageTopic) throws IOException {
    	//Se deserializa el mensaje al objeto Order
    	ObjectMapper objectMapper = new ObjectMapper();
    	Order order = objectMapper.readValue(messageTopic, Order.class);
    	//Se calcula el total y subtotal
    	calculateTotalPrice(order) ;
    	order.setShipDate(Utilities.convertDateToString( new Date()));
    	//Se crea la orden en base de datos
    	order = orders.createOrder(order);
    	//retorna la orden creada para publicarla en el topico
        return order; 
    }
    
    @Override
    public Order updateOrder(Order order) {
        return orders.updateOrder(order);
    }

    @Override
    public Order deleteOrder(Order order) {
        return orders.deleteOrder(order);
    }

    @Override
    public Order getOrder(Order order) {
        return orders.getOrder(order);
    }

    @Override
    public List<Order> getOrders() {
        return orders.getOrders();
    }
    /**
     * Calcula el Total y el subTotal de la orden
     * segun el precio unitario y la cantidad de productos
     * @param order , orden con los productos
     * @return orden con los valores calculados
     */
    protected void calculateTotalPrice(Order order) {
    	double total = 0;
    	for (Product product : order.getProducts()) {
    		total+=product.getPrice()*product.getQuantity();
		}
    	order.setTotal(total);
    	//Esto se realiza si no se va tener impuestos
    	order.setSubtotal(total);
	}
}

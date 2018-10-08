package com.udea.orders.bo.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.udea.orders.bo.OrdersBO;
import com.udea.orders.dto.Order;
import com.udea.orders.dto.OrderStatus;
import com.udea.orders.util.UtilDates;


@Component
public class OrdersBOImpl implements OrdersBO {

	@Override
	public Order createOrder(Order order) throws Exception {
		order.setShipDate(UtilDates.convertDateToString(new Date())); 
		order.setStatus(OrderStatus.CREATED);
		//conviertir dto a entity
		//persistencia
		return order;
	}

	@Override
	public Order reservedOrder(Order order) {
		order.setStatus(OrderStatus.RESERVED);
		//conviertir dto a entity
		//persistencia
		return order;
	}

	@Override
	public Order updateOrder(Order order) {
		return order;
	}

	@Override
	public Order deleteOrder(Order order) {
		order.setStatus(OrderStatus.DELETED);
		return order;
	}

	@Override
	public Order getOrder(Order order) {
		return order;
	}

	@Override
	public List<Order> getOrders() {
		return new LinkedList<>();
	}

}

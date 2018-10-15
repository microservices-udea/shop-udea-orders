package com.udea.orders.bo.impl;

import java.util.*;

import com.udea.orders.dao.OrderRepository;
import com.udea.orders.domain.Product;
import com.udea.orders.entities.Orders;
import com.udea.orders.entities.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udea.orders.bo.OrdersBO;
import com.udea.orders.domain.Order;
import com.udea.orders.enumeration.OrderStatus;
import com.udea.orders.util.UtilDates;


@Component
public class OrdersBOImpl implements OrdersBO {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) throws Exception {
        order.setShipDate(UtilDates.convertDateToString(new Date()));
        if(order.getTotal() > 0 && !order.getProducts().isEmpty()) {
            order.setStatus(OrderStatus.CREATED);
            Orders orders = orderRepository.save(toOrders(order));
            order.setId(String.valueOf(orders.getId()));
        }else{
            throw new Exception("Orden con total en cero o items vacios");
        }
        return order;
    }

    @Override
    public void reservedOrder(Order order) {
    	Integer idOrder= Integer.parseInt(order.getId());
    	Optional<Orders> orders = orderRepository.findById(idOrder);
    	if(orders.isPresent()){
    		orderRepository.save(toOrders(order));
    	}
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

    private Orders toOrders(Order order) {
        Orders orders = new Orders();
        orders.setComplete(order.isComplete());
        order.setEventType(order.getEventType());
        orders.setShipDate(order.getShipDate());
        orders.setStatus(order.getStatus());
        orders.setSubtotal(order.getSubtotal());
        orders.setTotal(order.getTotal());
        orders.setUserId(order.getUserId());

        Set<Products> productsList = new HashSet<>();
        Products product;
        for (Product productTmp : order.getProducts()) {
            product = new Products();
            product.setProductId(productTmp.getProductId());
            product.setQuantity(productTmp.getQuantity());
            product.setPrice(productTmp.getPrice());
            product.setOrder(orders);
            productsList.add(product);
        }

        orders.setProducts(productsList);
        return orders;
    }

}

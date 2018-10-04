package com.udea.orders.rs;

import com.udea.orders.dto.Order;
import com.udea.orders.dto.OrderStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderService orders;

    @Test
    public void createOrder() {
        Order order = new Order();
        order.setId("1");
        order.setComplete(false);
        Order orderResponse = orders.createOrder(order);
        Assert.assertEquals(orderResponse.getStatus(),OrderStatus.CREATED);
    }

    @Test
    public void getOrder() {
        Order order = new Order();
        order.setId("1");
        order.setComplete(false);
        Order orderResponse = orders.getOrder("1");
        Assert.assertEquals(orderResponse.getId(),"1");
    }

    @Test
    public void updateOrder() {
        Order order = new Order();
        order.setId("1");
        order.setStatus(OrderStatus.PLACED);
        Order orderResponse = orders.updateOrder("1",order);
        Assert.assertEquals(orderResponse.getId(),"1");
        Assert.assertEquals(orderResponse.getStatus(),OrderStatus.APRROVED);

    }

    @Test
    public void deleteOrder() {
        Order order = new Order();
        order.setId("1");
        order.setComplete(false);
        orders.createOrder(order);
        Order orderResponse = orders.deleteOrder("1");
        Assert.assertEquals(orderResponse.getId(),"1");
        Assert.assertEquals(orderResponse.getStatus(),OrderStatus.DELETED);
    }
}
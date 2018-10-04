package com.udea.orders.service;

import com.udea.orders.dto.Order;
import com.udea.orders.dto.OrderStatus;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceFacadeTest {

    @Autowired
    OrderServiceFacade orders;

    @Test
    public void createOrderTopic() {
        String message = "{\r\n" + 
        		"  \"user-id\": 1 ,\r\n" + 
        		"  \"products\": [{\r\n" + 
        		"    \"product-id\":1,\r\n" + 
        		"    \"quantity\":2,\r\n" + 
        		"    \"price\":3\r\n" + 
        		"  }]\r\n" + 
        		"}"; 
        Order orderResponse = null;
		try {
			orderResponse = orders.createOrderTopic(message);
		} catch (IOException e) {
			fail();
		}
        System.err.println("OrdenCreated"+orderResponse);
        Assert.assertEquals(orderResponse.getStatus(),OrderStatus.CREATED);
    }
}
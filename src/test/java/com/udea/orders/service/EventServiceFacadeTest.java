package com.udea.orders.service;

import com.udea.orders.domain.event.cart.CheckedOutEvent;
import com.udea.orders.dto.Order;
import com.udea.orders.dto.OrderStatus;
import com.udea.orders.util.DTOBuilder;
import com.udea.orders.util.EventMapper;

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
public class EventServiceFacadeTest {

	@Autowired
	private EventServiceFacade eventService;

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
		CheckedOutEvent checkedOutEvent;
		try {
			checkedOutEvent = EventMapper.toCheckedOutEvent(message);
			Order order = DTOBuilder.toOrderDTO(checkedOutEvent);
			Order orderResponse = eventService.createdOrder(order);
			System.err.println("OrdenCreated" + orderResponse);
			Assert.assertEquals(orderResponse.getStatus(), OrderStatus.CREATED);

		} catch (IOException e) {
			fail();
		}

	}
}
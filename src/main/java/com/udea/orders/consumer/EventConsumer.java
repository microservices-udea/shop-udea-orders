package com.udea.orders.consumer;

import com.udea.orders.domain.event.cart.CheckedOutEvent;
import com.udea.orders.domain.event.catalog.InventoryReservedEvent;
import com.udea.orders.dto.Order;
import com.udea.orders.service.EventServiceFacade;
import com.udea.orders.util.DTOBuilder;
import com.udea.orders.util.EventMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
class EventConsumer {

    @Autowired
    private EventServiceFacade eventService;

    @KafkaListener(topics = "${topic.cart}")
    public void processOrderCreated(String event,
                             @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                             @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                             @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
    	try {
    		CheckedOutEvent checkedOutEvent=	EventMapper.toCheckedOutEvent(event);
    		Order order = DTOBuilder.toOrderDTO(checkedOutEvent);
    		eventService.createdOrder(order);
		} catch (IOException e) {
			//Rechazar orden
			
 		}
        System.out.printf("%s-%d[%d] \"%s\"\n", topics.get(0), partitions.get(0), offsets.get(0), event);
    }

    @KafkaListener(topics = "${topic.inventory}")
    public void processOrderReserved(String event,
                             @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                             @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                             @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
    	try {
    		InventoryReservedEvent inventoryReservedEvent=	EventMapper.toInventoryReservedEvent(event);
    		Order order = DTOBuilder.toOrderDTO(inventoryReservedEvent);
    		eventService.reservedOrder(order);
		} catch (IOException e) {
			//Rechazar orden
			
 		}
        System.out.printf("%s-%d[%d] \"%s\"\n", topics.get(0), partitions.get(0), offsets.get(0), event);
    }

    @KafkaListener(topics = "${topic.payments}")
    public void processOrderPaid(String event,
                                     @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                                     @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                                     @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        System.out.printf("%s-%d[%d] \"%s\"\n", topics.get(0), partitions.get(0), offsets.get(0), event);
    }

    @KafkaListener(topics = "${topic.accounting}")
    public void processOrderCompleted(String event,
                                 @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                                 @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                                 @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        System.out.printf("%s-%d[%d] \"%s\"\n", topics.get(0), partitions.get(0), offsets.get(0), event);
    }
}
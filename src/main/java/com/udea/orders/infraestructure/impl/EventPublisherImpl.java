package com.udea.orders.infraestructure.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.udea.orders.infraestructure.EventPublisher;
import com.udea.orders.domain.Order;
import com.udea.orders.util.EventMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventPublisherImpl implements EventPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;


    @Value("${topic.orders}")
    private String topic;

    EventPublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishEvent(Order order) {
    	try {
    		String data= "";
    		switch (order.getStatus()) {
    		case CREATED:
    			data =  EventMapper.toOrderCreated(order);
				break;
    		case RESERVED: 
    			data=  EventMapper.toOrderReserved(order);
				break;
    		case REJECTED:
				data=  EventMapper.toOrderRejected(order);
    			break;

    		default:
    			break;
    		}
    		this.kafkaTemplate.send(topic, data);
    		System.out.println("Sent sample message [" + order.toString() + "] to " + topic);
    	} catch (JsonProcessingException e) {
    		e.printStackTrace();
    	}

    }

}

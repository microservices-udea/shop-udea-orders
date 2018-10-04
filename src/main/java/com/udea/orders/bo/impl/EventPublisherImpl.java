package com.udea.orders.bo.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.udea.orders.bo.EventPublisher;
import com.udea.orders.dto.Order;
import com.udea.orders.util.Utilities;
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
            this.kafkaTemplate.send(topic, Utilities.toOrderCreated(order));
            System.out.println("Sent sample message [" + order.toString() + "] to " + topic);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}

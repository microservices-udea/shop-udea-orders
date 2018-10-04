package com.udea.orders.bo.impl;

import com.udea.orders.bo.EventPublisher;
import com.udea.orders.dto.Order;
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
        this.kafkaTemplate.send(topic, order.toString());
        System.out.println("Sent sample message [" + order.toString() + "] to " + topic);
    }

}

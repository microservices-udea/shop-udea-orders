package com.udea.orders.infraestructure;

import com.udea.orders.adapters.EventServiceAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class EventConsumer {


    @Qualifier("InventoryEventAdapter")
    @Autowired
    private EventServiceAdapter inventoryEventAdapter;

    @Qualifier("CartEventAdapter")
    @Autowired
    private EventServiceAdapter cartEventAdapter;

    @KafkaListener(topics = "${topic.cart}")
    public void processOrderCreated(String event,
                                    @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                                    @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                                    @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        cartEventAdapter.processEvent(event);
        System.out.printf("%s-%d[%d] \"%s\"\n", topics.get(0), partitions.get(0), offsets.get(0), event);
    }

    @KafkaListener(topics = "${topic.inventory}")
    public void processOrderReserved(String event,
                                     @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                                     @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                                     @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        inventoryEventAdapter.processEvent(event);
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
package com.udea.orders.adapters.impl;

import com.udea.orders.adapters.EventServiceAdapter;
import com.udea.orders.dto.events.catalog.InventoryReservedEvent;
import com.udea.orders.service.EventServiceFacade;
import com.udea.orders.util.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Qualifier("InventoryEventAdapter")
@Component
public class InventoryEventAdapter implements EventServiceAdapter {

    @Autowired
    private InventoryEventAdaptee inventoryEventAdaptee;

    @Autowired
    private EventServiceFacade eventService;


    @Override
    public void processEvent(String event) {
        try {
            InventoryReservedEvent inventoryReservedEvent = EventMapper.toInventoryReservedEvent(event);
            eventService.reservedOrder(inventoryEventAdaptee.inventoryReserved(inventoryReservedEvent));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

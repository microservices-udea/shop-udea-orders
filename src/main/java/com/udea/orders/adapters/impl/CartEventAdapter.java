package com.udea.orders.adapters.impl;

import com.udea.orders.adapters.EventServiceAdapter;
import com.udea.orders.dto.events.cart.CheckedOutEvent;
import com.udea.orders.service.EventServiceFacade;
import com.udea.orders.util.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Qualifier("CartEventAdapter")
@Component
public class CartEventAdapter implements EventServiceAdapter {

    @Autowired
    private CartEventAdaptee cartEvent;

    @Autowired
    private EventServiceFacade eventService;


    @Override
    public void processEvent(String event) {
        try {
            CheckedOutEvent checkedOut = EventMapper.toCheckedOutEvent(event);
            eventService.createdOrder(cartEvent.checkedOut(checkedOut));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

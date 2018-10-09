package com.udea.orders.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.udea.orders.dto.events.cart.CheckedOutEvent;
import com.udea.orders.dto.events.catalog.InventoryReservedEvent;
import com.udea.orders.domain.Order;

public class EventMapper {

	 /**
     * Metodo que transforma el mensaje recibido del topico de chekout
     * a un evento CartEventAdapter
     * @param messageCheckout a trasformar
     * @return evento de CartEventAdapter
	 * @throws IOException 
     */
    public static CheckedOutEvent toCheckedOutEvent(String messageCheckout) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CheckedOutEvent checkedOutEvent = objectMapper.readValue(messageCheckout, CheckedOutEvent.class);

        return checkedOutEvent;
    }
	 /**
     * Metodo que transforma el mensaje recibido del topico de inventory
     * a un evento InventoryReservedEvent
     * @param messageCheckout a trasformar
     * @return evento de InventoryReservedEvent
	 * @throws IOException 
     */
    public static InventoryReservedEvent toInventoryReservedEvent(String messageCheckout) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InventoryReservedEvent inventoryReservedEvent = objectMapper.readValue(messageCheckout, InventoryReservedEvent.class);

        return inventoryReservedEvent;
    }
    /**
     * Metodo que transforma la orden recibida a una orden con la informacion
     * a publicar como evento de Orden Creada
     *
     * @param order . orden a transformar
     * @return orden orden creada
     * @throws JsonProcessingException Excepci�n generada sino puede serializar el evento
     */
    public static String toOrderCreated(Order order) throws JsonProcessingException {
        order.setEventOrder("ordenCreated");
        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilterOrder = SimpleBeanPropertyFilter
                .filterOutAllExcept("order-id", "user-id", "products", "event-id");
        SimpleBeanPropertyFilter theFilterProduct = SimpleBeanPropertyFilter
                .filterOutAllExcept("product-id", "quantity");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filterOrder", theFilterOrder)
                .addFilter("filterProduct", theFilterProduct);

        return mapper.writer(filters).writeValueAsString(order);
    }

    /**
     * Metodo que transforma la orden recibida a una orden con la informacion
     * a publicar como evento de Orden reservada
     *
     * @param order . orden a transformar
     * @return orden orden reservada
     * @throws JsonProcessingException Excepcion generada sino puede serializar el evento
     */
    public static String toReservedOrder(Order order) throws JsonProcessingException {
    	order.setEventOrder("reservedOrder");
    	ObjectMapper mapper = new ObjectMapper();
    	SimpleBeanPropertyFilter theFilterOrder = SimpleBeanPropertyFilter
    			.filterOutAllExcept("order-id", "user-id","subtotal", "total", "event-type");
    	FilterProvider filters = new SimpleFilterProvider()
    			.addFilter("filterOrder", theFilterOrder);

    	return mapper.writer(filters).writeValueAsString(order);
    }
}

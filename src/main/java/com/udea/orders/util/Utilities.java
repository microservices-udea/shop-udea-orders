package com.udea.orders.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.udea.orders.dto.Order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

    private static final String formatDate = "dd/MM/yyyy";

    /**
     * Metodo que transforma la orden recibida a una orden con la informacion
     * a publicar como evento de Orden Creada
     *
     * @param order . orden a transformar
     * @return orden orden creada
     * @throws JsonProcessingException Excepción generada sino puede serializar el evento
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

    public static String convertDateToString(Date dt) {
        DateFormat df = new SimpleDateFormat(formatDate);
        return df.format(dt);
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

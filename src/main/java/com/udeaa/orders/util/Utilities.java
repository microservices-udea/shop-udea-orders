package com.udeaa.orders.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.udea.orders.dto.Order;

public class Utilities {

	private static final String formatDate="dd/MM/yyyy";
    /**
     * Metodo que transforma la orden recibida a una orden con la informacion
     * a publicar como evento de Orden Creada
     * @param order . orden a transformar
     * @return orden creada
     * @throws JsonProcessingException 
     */
	public static String toOrderCreated(Order order) throws JsonProcessingException {
		order.setEventOrder("ordenCreated");
		ObjectMapper mapper = new ObjectMapper();
	    SimpleBeanPropertyFilter theFilterOrder = SimpleBeanPropertyFilter
	    		.filterOutAllExcept("order-id","user-id","products","event-id");
	    SimpleBeanPropertyFilter theFilterProduct= SimpleBeanPropertyFilter
	    		.filterOutAllExcept("product-id","quantity");
	    FilterProvider filters = new SimpleFilterProvider()
	      .addFilter("filterOrder", theFilterOrder)
	      .addFilter("filterProduct", theFilterProduct);

	    String dtoAsString = mapper.writer(filters).writeValueAsString(order);
		
	    return dtoAsString;
	}
	
	public static String convertDateToString(Date dt) {
        DateFormat df = new SimpleDateFormat(formatDate);
        String dateToString = df.format(dt);
        return dateToString;
    }
}

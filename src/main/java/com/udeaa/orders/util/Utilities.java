package com.udeaa.orders.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.udea.orders.dto.Order;

public class Utilities {
	
	private static final String formatDate="dd/MM/yyyy";
    /**
     * Metodo que transforma la orden recibida a una orden con la informacion
     * a publicar como evento de Orden Creada
     * @param order . orden a transformar
     * @return orden creada
     */
	public static Order toOrderCreated(Order order) {
		
		return order;
	}
	
	public static String convertDateToString(Date dt) {
        DateFormat df = new SimpleDateFormat(formatDate);
        String dateToString = df.format(dt);
        return dateToString;
    }
}

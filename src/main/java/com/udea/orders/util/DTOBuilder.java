package com.udea.orders.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.udea.orders.domain.event.cart.CheckedOutEvent;
import com.udea.orders.domain.event.cart.ProductCart;
import com.udea.orders.domain.event.catalog.InventoryReservedEvent;
import com.udea.orders.dto.Order;
import com.udea.orders.dto.Product;

public class DTOBuilder {

	/**
	 * Metodo que convierte el CheckedOutEvent en un objeto de tipo Order
	 * @param checkedOutEvent
	 * @return DTO de tipo order
	 * @throws IOException 
	 */
	public static Order toOrderDTO(CheckedOutEvent checkedOutEvent){

		Order order = new Order();
		List<Product> products = new ArrayList<>(); 
		order.setId(checkedOutEvent.getOrderId());
		order.setUserId(checkedOutEvent.getUserId());

		for ( ProductCart productCart : checkedOutEvent.getProducts()) {
			Product product = new Product();
			product.setPrice(productCart.getPrice());
			product.setProductId(productCart.getProductId());
			product.setQuantity(productCart.getQuantity());
			products.add(product);
		}
		order.setProducts(products);
		return order;
	}
	/**
	 * Metodo que convierte el InventoryReservedEvent en un objeto de tipo Order
	 * @param inventoryReservedEvent
	 * @return DTO de tipo order
	 * @throws IOException 
	 */
	public static Order toOrderDTO(InventoryReservedEvent inventoryReservedEvent){

		Order order = new Order();
		order.setId(inventoryReservedEvent.getOrderId());
		order.setUserId(inventoryReservedEvent.getUserId());
		order.setEventOrder(inventoryReservedEvent.getEventType());
		return order;
	}
}

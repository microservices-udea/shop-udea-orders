package com.udea.orders.domain.event.cart;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckedOutEvent {
	
	@JsonProperty("order-id")
    private String orderId;

    @JsonProperty("user-id")
    private String userId;

    @JsonProperty("products")
    private List<ProductCart> products;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ProductCart> getProducts() {
        return products;
    }

    public void setProducts(LinkedList<ProductCart> products) {
        this.products = products;
    }

    
}

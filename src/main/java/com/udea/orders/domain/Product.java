package com.udea.orders.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFilter("filterProduct")
public class Product {

    @JsonProperty("product-id")
    private String productId;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("price")
    private double price;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", quantity=" + quantity + ", price=" + price + "]";
	}
    
}

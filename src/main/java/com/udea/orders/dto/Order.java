package com.udea.orders.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedList;
import java.util.List;

@JsonFilter("filterOrder")
public class Order {
	
	@JsonProperty("order-id")
    @ApiModelProperty(hidden = true)
    private String id;

    @JsonProperty("user-id")
    private String userId;

    @JsonProperty("ship-date")
    private String shipDate;

    @JsonProperty("products")
    private List<Product> products;

    @JsonProperty("subtotal")
    private double subtotal;

    @JsonProperty("total")
    private double total;

    @ApiModelProperty(hidden = true)
    private OrderStatus status;

    @ApiModelProperty(hidden = true)
    private boolean complete;
    
    @JsonProperty("event-type")
    private String eventOrder;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(LinkedList<Product> products) {
        this.products = products;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

	public String getEventOrder() {
		return eventOrder;
	}

	public void setEventOrder(String eventOrder) {
		this.eventOrder = eventOrder;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", shipDate=" + shipDate + ", products=" + products
				+ ", subtotal=" + subtotal + ", total=" + total + ", status=" + status + ", complete=" + complete
				+ ", eventOrder=" + eventOrder + "]";
	}

	
    
}

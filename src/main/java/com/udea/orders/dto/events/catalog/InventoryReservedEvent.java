package com.udea.orders.dto.events.catalog;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InventoryReservedEvent {
	
	@JsonProperty("order-id")
    private String orderId;

    @JsonProperty("user-id")
    private String userId;

    @JsonProperty("event-type")
    private String eventType;

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
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
   
}
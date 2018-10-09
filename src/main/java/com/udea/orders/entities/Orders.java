package com.udea.orders.entities;

import com.udea.orders.enumeration.OrderStatus;

import javax.persistence.*;
import java.util.List;

@Entity
public class Orders {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "ship_date")
    private String shipDate;

    @OneToMany(mappedBy = "order")
    private List<Products> products;

    @Column(name = "subtotal")
    private double subtotal;

    @Column(name = "total")
    private double total;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "complete")
    private boolean complete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
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
}

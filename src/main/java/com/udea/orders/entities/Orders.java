package com.udea.orders.entities;

import com.udea.orders.enumeration.OrderStatus;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Orders {

    @Column(name = "order_id")
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "ship_date")
    private String shipDate;

    private Set<Products> products;

    @Column(name = "subtotal")
    private double subtotal;

    @Column(name = "total")
    private double total;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "complete")
    private boolean complete;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    public Set<Products> getProducts() {
        return products;
    }

    public void setProducts(Set<Products> products) {
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

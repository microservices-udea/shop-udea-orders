package com.udea.orders.adapters.impl;

import com.udea.orders.dto.events.cart.CheckedOutEvent;
import com.udea.orders.dto.events.cart.ProductCart;
import com.udea.orders.domain.Order;
import com.udea.orders.domain.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartEventAdaptee {


    /**
     * Metodo que convierte el CheckedOutEvent en un objeto de tipo Order
     * @param event CheckedOutEvent
     * @return Order
     */
    public Order checkedOut(CheckedOutEvent event) {
        Order order = new Order();
        List<Product> products = new ArrayList<>();
        order.setId(event.getOrderId());
        order.setUserId(event.getUserId());

        if (event.getProducts() != null) {
            for (ProductCart productCart : event.getProducts()) {
                Product product = new Product();
                product.setPrice(productCart.getPrice());
                product.setProductId(productCart.getProductId());
                product.setQuantity(productCart.getQuantity());
                products.add(product);
            }
        }
        order.setProducts(products);
        return order;
    }
}

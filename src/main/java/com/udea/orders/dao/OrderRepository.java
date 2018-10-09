package com.udea.orders.dao;

import com.udea.orders.entities.Orders;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Integer>{
}

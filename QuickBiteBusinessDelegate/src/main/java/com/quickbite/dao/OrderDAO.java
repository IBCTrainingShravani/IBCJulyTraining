package com.quickbite.dao;

import java.util.List;

import com.quickbite.model.Order;

public interface OrderDAO {
	public void save(Order order);

	public Order findById(String orderId);

	public List<Order> findAll();

}

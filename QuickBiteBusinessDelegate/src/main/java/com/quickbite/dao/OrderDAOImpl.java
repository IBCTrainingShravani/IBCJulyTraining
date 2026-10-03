package com.quickbite.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.quickbite.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Order order) {

		sessionFactory.getCurrentSession().persist(order);
	}

	@Override
	public Order findById(String orderId) {

		return sessionFactory.getCurrentSession().get(Order.class, orderId);
	}

	@Override
	public List<Order> findAll() {

		return sessionFactory.getCurrentSession().createQuery("from Order", Order.class).list();
	}
}
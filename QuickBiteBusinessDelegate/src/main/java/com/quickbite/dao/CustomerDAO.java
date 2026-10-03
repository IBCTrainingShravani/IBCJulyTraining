package com.quickbite.dao;

import java.util.List;

import com.quickbite.model.Customer;

public interface CustomerDAO {

	void save(Customer customer);

	Customer findById(int id);

	List<Customer> findAll();

	Customer findByEmail(String email);
}

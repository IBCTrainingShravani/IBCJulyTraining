package com.quickbite.dao;

import java.util.List;

import com.quickbite.model.Restaurant;

public interface RestaurantDAO extends RepositoryDAO<Restaurant> {
	public void save(Restaurant restaurant);

	public Restaurant findById(int id);

	public List<Restaurant> findAll();

}

package com.quickbite.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quickbite.model.Restaurant;

@Repository
public class RestaurantDAOImpl
        implements RestaurantDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Restaurant restaurant) {

        sessionFactory
                .getCurrentSession()
                .persist(restaurant);
    }

    @Override
    public Restaurant findById(int id) {

        return sessionFactory
                .getCurrentSession()
                .get(Restaurant.class, id);
    }

    @Override
    public List<Restaurant> findAll() {

        return sessionFactory
                .getCurrentSession()
                .createQuery(
                    "from Restaurant",
                    Restaurant.class)
                .list();
    }

	@Override
	public void save(int id, Restaurant entity) {
		// TODO Auto-generated method stub
		 
		
	}
}

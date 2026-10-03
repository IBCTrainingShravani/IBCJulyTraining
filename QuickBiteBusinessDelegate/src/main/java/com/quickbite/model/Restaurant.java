package com.quickbite.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="restaurant")
public class Restaurant {
	@Id
	private int restaurantId;
	private String restaurantName;
	private String location;
	private double rating;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="restaurant_id")
	private List<MenuItem> menuItems=new ArrayList<>();


	public Restaurant(int restaurantId, String restaurantName, String location, double rating) {
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.location = location;
		this.rating = rating;
		this.menuItems = new ArrayList<>();
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public String getLocation() {
		return location;
	}

	public double getRating() {
		return rating;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void addMenuItem(MenuItem item) {
		this.menuItems.add(item);
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", location="
				+ location + ", rating=" + rating + ", menuItems=" + menuItems + "]";
	}
	
}

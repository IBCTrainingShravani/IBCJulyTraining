package com.quickbite.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	@Id
	private String orderId;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="restaurant_id")
	private Restaurant restaurant;
	
	@ManyToMany
	@JoinTable(name="order_items",joinColumns=@JoinColumn(name="order_id"),inverseJoinColumns=@JoinColumn(name="item_id"))
	private List<MenuItem> foodItems;
	private double totalAmount;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	public Order() {}


	public Order(Customer customer, Restaurant restaurant, List<MenuItem> foodItems, double totalAmount) {
		
		this.customer = customer;
		this.restaurant = restaurant;
		this.foodItems = foodItems;
		this.totalAmount = totalAmount;
		this.orderStatus = OrderStatus.PLACED;
	}

	

	public String getOrderId() {
		return orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public List<MenuItem> getFoodItems() {
		return foodItems;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String generateInvoice() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n======================================================\n");
		sb.append("                 QUICKBITE OFFICIAL INVOICE          \n");
		sb.append("======================================================\n");
		sb.append("Order Ref    : ").append(orderId).append("\n");
		sb.append("Date         : ").append(new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(new Date()))
				.append("\n");
		sb.append("Customer     : ").append(customer.getName()).append(" (").append(customer.getMobile()).append(")\n");
		sb.append("Restaurant   : ").append(restaurant.getRestaurantName()).append("\n");
		sb.append("------------------------------------------------------\n");
		sb.append("Items Ordered:\n");

		Iterator<MenuItem> iterator = foodItems.iterator();
		while (iterator.hasNext()) {
			MenuItem item = iterator.next();
			sb.append(String.format(" - %-25s : ₹%.2f\n", item.getItemName(), item.getPrice()));
		}
		sb.append("------------------------------------------------------\n");
		sb.append(String.format("TOTAL AMOUNT PAID : ₹%.2f\n", totalAmount));
		sb.append("Status            : ").append(orderStatus).append("\n");
		sb.append("======================================================\n");
		return sb.toString();
	}
}

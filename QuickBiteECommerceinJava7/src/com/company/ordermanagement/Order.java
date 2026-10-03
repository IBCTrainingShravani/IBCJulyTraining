package com.company.ordermanagement;

import java.util.Date;

public class Order {

	private int orderId;
	private Customer customer;
	private double amount;
	private String category;
	private Date orderDate;
	
	/*orders.add(new Order(101, john, 45000.00, "Electronics", sdf.parse("2025-01-10")));
	orders.add(new Order(102, david, 5000.00, "Books", sdf.parse("2025-02-10")));
	orders.add(new Order(101, smith, 50000.00, "Fashion", sdf.parse("2025-03-15")));
	orders.add(new Order(101, john, 40000.00, "Electronics", sdf.parse("2025-04-10")));*/

	public Order(int orderId, Customer customer, double amount, String category, Date orderDate) {
		this.orderId = orderId;
		this.customer = customer;
		this.amount = amount;
		this.category = category;
		this.orderDate = orderDate;

	}

	public int getOrderId() {
		return orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public double getAmount() {
		return amount;
	}

	public String getCategory() {
		return category;

	}

	public Date getOrderDate() {
		return orderDate;

	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customer=" + customer + ", amount=" + amount + ", category=" + category
				+ ", orderDate=" + orderDate + "]";
	}

}

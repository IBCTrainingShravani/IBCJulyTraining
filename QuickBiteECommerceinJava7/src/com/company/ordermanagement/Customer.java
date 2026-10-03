package com.company.ordermanagement;

public class Customer {

	private int customerId;
	private String name;
	private String city;
	private boolean premium;

	/*Customer john = new Customer(1, "JOHN", "Hyderabad", true);
	Customer smith = new Customer(2, "SMITH", "Bangalore", true);
	Customer david = new Customer(3, "David", "Chennai", false);*/

	public Customer(int customerId, String name, String city, boolean premium) {
		this.customerId = customerId;
		this.name = name;
		this.city = city;
		this.premium = premium;

	}

	public int getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public boolean isPremium() {
		return premium;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", city=" + city + ", premium=" + premium
				+ "]";
	}

}

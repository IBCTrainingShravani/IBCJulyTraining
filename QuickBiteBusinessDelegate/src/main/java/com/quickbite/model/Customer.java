package com.quickbite.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends User {
	private String address;
	private double walletBalance;

	public Customer() {

	}



	public Customer(int id, String name, String email, long mobile, String address, double walletBalance) {
		super(id, name, email, mobile);
		this.address = address;
		this.walletBalance = new Double(walletBalance);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getWalletBalance() {
		return walletBalance;
	}
	public void setWalletBalance(Double walletBalance) {
		this.walletBalance=walletBalance;
	}

	@Override
	public void login() {
		System.out.println(">> Customer [" + getName() + "] logged in successfully.");
	}

	@Override
	public void logout() {
		System.out.println("<< Customer [" + getName() + "] logged out.");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Customer customer = (Customer) o;
		return getId() == customer.getId() && Objects.equals(getEmail(), customer.getEmail());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getEmail());
	}

	@Override
	public String toString() {
		return super.toString() + "Customer [address=" + address + ", wallet=" + walletBalance + "]";
	}

}
package com.hotel.shmrs.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer implements Serializable, Comparable<Customer> {
	private static final long serialVersionUID = 1L;

	private final String customerId;
	private final String name;
	private final String email;
	private final boolean isVIP;
	private final Passport passport;
	private final List<HotelService> optedServices = new ArrayList<>();

	private final transient String maskedSecretPin;
	
	/*Customer guestA = new Customer("C-101", "Arjun Verma", "arjun@example.com", false,
			new Passport("L982341", "IND"), "PIN_8899");
	Customer guestB = new Customer("C-102", "Priya Sharma", "priya@example.com", true,
			new Passport("K112233", "IND"), "PIN_4411");

	guestB.addService(new HotelService("Luxury Airport Cab", 1800.0));
	guestB.addService(new HotelService("Spa & Sauna Access", 2500.0));*/


	public Customer(String customerId, String name, String email, boolean isVIP, Passport passport,
			String maskedSecretPin) {
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.isVIP = isVIP;
		this.passport = passport;
		this.maskedSecretPin = maskedSecretPin;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public boolean isVIP() {
		return isVIP;
	}

	public Passport getPassport() {
		return passport;
	}

	public List<HotelService> getOptedServices() {
		return optedServices;
	}

	public String getMaskedSecretPin() {
		return maskedSecretPin;
	}
	
	/*guestB.addService(new HotelService("Luxury Airport Cab", 1800.0));
	guestB.addService(new HotelService("Spa & Sauna Access", 2500.0));*/

	public void addService(HotelService service) {
		this.optedServices.add(service);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Customer customer))
			return false;
		return Objects.equals(customerId, customer.customerId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId);
	}

	@Override
	public int compareTo(Customer o) {
		return this.customerId.compareTo(o.customerId);
	}

	@Override
	public String toString() {
		return "Customer[ID=" + customerId + ", Name=" + name + ", VIP=" + isVIP + ", " + passport + ", SecretPIN="
				+ (maskedSecretPin == null ? "NULL/PROTECTED" : maskedSecretPin) + "]";
	}
}
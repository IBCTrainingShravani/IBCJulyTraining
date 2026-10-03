package com.hotel.shmrs.model.entity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomerAndPassportTest {

	@Test
	@DisplayName("Should create customer with passport and verify details")
	void testCustomerAndPassportIntegrity() {

		Passport passport = new Passport("A12344678", "US");
		Customer customer = new Customer("CUST-001", "Alice Smith", "alice@example.com", true, passport, "PIN_4567");

		assertAll("Customer & Passport Properties", () -> assertEquals("CUST-001", customer.getCustomerId()),
				() -> assertEquals("Alice Smith", customer.getName()),
				() -> assertEquals("alice@example.com", customer.getEmail()), () -> assertTrue(customer.isVIP()),
				() -> assertEquals("PIN_4567", customer.getMaskedSecretPin()),
				() -> assertNotNull(customer.getPassport()),
				() -> assertEquals("A12344678", customer.getPassport().getPassportNumber()),
				() -> assertEquals("US", customer.getPassport().getCountryCode()));

	}

	@Test
	@DisplayName("Should handle opt0-in services correctly")
	void testOptedServices() {
		Passport passport = new Passport("K4499221", "IND");
		Customer customer = new Customer("C-102", "Ananya Venkatesh", "ananya@example.com", true, passport, "PIN_7722");

		HotelService cabService = new HotelService("Luxury Airport Cab", 1800.0);
		HotelService spaService = new HotelService("Ayurvedic Spa & Sauna", 2500.0);

		customer.addService(cabService);
		customer.addService(spaService);

		List<HotelService> services = customer.getOptedServices();

		assertAll("Opted Services Verification", () -> assertEquals(2, services.size()),
				() -> assertEquals("Luxury Airport Cab", services.get(0).getServiceName()),
				() -> assertEquals(1800.0, services.get(0).getCost()),
				() -> assertEquals("Ayurvedic Spa & Sauna", services.get(1).getServiceName()),
				() -> assertEquals(2500.0, services.get(1).getCost()));

	}

}

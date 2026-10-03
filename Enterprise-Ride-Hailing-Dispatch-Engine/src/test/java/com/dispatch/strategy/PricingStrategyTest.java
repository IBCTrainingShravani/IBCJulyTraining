package com.dispatch.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.dispatch.enums.RideType;
import com.dispatch.models.Location;
import com.dispatch.models.Ride;
import com.dispatch.models.Rider;

class PricingStrategyTest {

	private Rider testRider;
	private Location pickup;
	private Location dropoff;

	@BeforeEach
	void setUp() {
		pickup = new Location(12.9350, 77.6240);
		dropoff = new Location(12.9716, 77.5946);
		testRider = new Rider("USR-1", "Test Rider", pickup);
	}

	@Test
	@DisplayName("StandardPricingStrategy should calculate standard fare without surge")
	void testStandardPricingCalculation() {
		PricingStrategy strategy = new StandardPricingStrategy();
		Ride ride = new Ride("RIDE-01", testRider, pickup, dropoff, RideType.SEDAN);

		
		double fare = strategy.calculateFare(ride, 10.0);

		assertEquals(200.0, fare, 0.001);
		assertEquals(1.0, ride.getSurgeMultiplier(), 0.001);
	}

	@ParameterizedTest
	@CsvSource({ "AUTO, 10.0, 234.0", "SEDAN, 10.0, 360.0", "SUV, 10.0, 540.0" })
	@DisplayName("RainSurgePricingStrategy should apply 1.8x multiplier across vehicle types")
	void testRainSurgePricing(RideType rideType, double distanceKm, double expectedFare) {
		PricingStrategy strategy = new RainSurgePricingStrategy();
		Ride ride = new Ride("RIDE-02", testRider, pickup, dropoff, rideType);

		double fare = strategy.calculateFare(ride, distanceKm);

		assertEquals(expectedFare, fare, 0.001);
		assertEquals(1.8, ride.getSurgeMultiplier(), 0.001);
	}

	@Test
	@DisplayName("PeakTrafficPricingStrategy should apply 2.4x traffic multiplier")
	void testPeakTrafficPricing() {
		PricingStrategy strategy = new PeakTrafficPricingStrategy();
		Ride ride = new Ride("RIDE-03", testRider, pickup, dropoff, RideType.AUTO);

		// AUTO: (30 + 5 * 10) * 2.4 = 80 * 2.4 = 192.0
		double fare = strategy.calculateFare(ride, 5.0);

		assertEquals(192.0, fare, 0.001);
		assertEquals(2.4, ride.getSurgeMultiplier(), 0.001);
	}
}
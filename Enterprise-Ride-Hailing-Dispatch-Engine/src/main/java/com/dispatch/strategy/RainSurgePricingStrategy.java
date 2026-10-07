package com.dispatch.strategy;

import com.dispatch.models.Ride;

public class RainSurgePricingStrategy implements PricingStrategy {

	private static final double RAIN_MULTIPLIER = 1.8;

	@Override
	public double calculateFare(Ride ride, double distanceKm) {
		ride.setSurgeMultiplier(RAIN_MULTIPLIER);
		double base = ride.getRideType().getBasePrice() + (distanceKm * ride.getRideType().getPerKmPrice());
		return base * RAIN_MULTIPLIER;
	}
}

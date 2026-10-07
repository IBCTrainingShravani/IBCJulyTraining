package com.dispatch.strategy;

import com.dispatch.models.Ride;

public class StandardPricingStrategy implements PricingStrategy {

	@Override
	public double calculateFare(Ride ride, double distanceKm) {
		// TODO Auto-generated method stub
		ride.setSurgeMultiplier(1.0);
		return ride.getRideType().getBasePrice() + (distanceKm * ride.getRideType().getPerKmPrice());
	}

}

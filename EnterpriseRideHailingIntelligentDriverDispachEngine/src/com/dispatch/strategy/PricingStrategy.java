package com.dispatch.strategy;

import com.dispatch.models.Ride;

public interface PricingStrategy {

	double calculateFare(Ride ride, double distanceKm);
}

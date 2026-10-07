package com.dispatch.observer;

import com.dispatch.models.Location;

public interface DriverObserver {
	void onRideRequested(String rideId, Location pickup);

}

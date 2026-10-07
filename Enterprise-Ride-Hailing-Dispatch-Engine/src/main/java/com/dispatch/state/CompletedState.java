package com.dispatch.state;

import com.dispatch.models.Driver;
import com.dispatch.models.Ride;

public class CompletedState implements RideState {
	@Override
	public void handleMatch(Ride ride, Driver driver) {
	}

	@Override
	public void handleStart(Ride ride) {
	}

	@Override
	public void handleComplete(Ride ride) {
	}

	@Override
	public void handleCancel(Ride ride) {
	}
}

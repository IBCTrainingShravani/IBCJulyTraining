package com.dispatch.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.dispatch.enums.DriverStatus;
import com.dispatch.observer.DriverObserver;

public class Driver extends BaseEntity implements DriverObserver {

	private Location location;
	private DriverStatus status;
	private final ReentrantLock lock = new ReentrantLock(true); // Explicit fair lock
	private double earnings;
	// One-to-Many Relationship: Driver has many completed rides
	private final List<Ride> completedRides;
	
	/*// 2. Populate Driver Registry in Cluster (Koramangala, Bengaluru)
	Driver d1 = new Driver("DRV-1", "Kishore Kumar", new Location(12.9352, 77.6245));
	Driver d2 = new Driver("DRV-2", "Sunil Gavaskar", new Location(12.9358, 77.6250));
	Driver d3 = new Driver("DRV-3", "Kapil Dev", new Location(12.9340, 77.6220));*/

	public Driver(String id, String name, Location location) {
		super(id, name);
		this.location = location;
		this.status = DriverStatus.AVAILABLE;
		this.earnings = 0.0;
		this.completedRides = new ArrayList<>();
	}

	public boolean tryAcquireLock() {
		return lock.tryLock();
	}

	public void releaseLock() {
		if (lock.isHeldByCurrentThread()) {
			lock.unlock();
		}
	}

	
	public void onRideRequested(String rideId, Location pickup) {
		System.out.println(">> [OBSERVER ALERT] Driver " + getName() + " (" + getId() + ") notified for Ride: " + rideId
				+ " at " + pickup);
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public DriverStatus getStatus() {
		return status;
	}

	public void setStatus(DriverStatus status) {
		this.status = status;
	}

	public double getEarnings() {
		return earnings;
	}

	public void addEarnings(double amount) {
		this.earnings += amount;
	}

	public void recordCompletedTrip(Ride ride) {
		this.completedRides.add(ride);
	}

	public List<Ride> getCompletedRides() {
		return Collections.unmodifiableList(completedRides);
	}
}

package com.hotel.shmrs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hotel.shmrs.model.entity.Booking;

public class InMemoryBookingDAO implements BookingDAO {
	private final Map<String, Booking> storage = new ConcurrentHashMap<>();

	@Override
	public void save(Booking booking) {
		storage.put(booking.getBookingId(), booking);
	}

	@Override
	public Booking findById(String bookingId) {
		return storage.get(bookingId);
	}

	@Override
	public List<Booking> findAll() {
		return new ArrayList<>(storage.values());
	}
}
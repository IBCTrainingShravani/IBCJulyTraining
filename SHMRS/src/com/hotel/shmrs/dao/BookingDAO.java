package com.hotel.shmrs.dao;

import java.util.List;

import com.hotel.shmrs.model.entity.Booking;

public interface BookingDAO {
	void save(Booking booking);

	Booking findById(String bookingId);

	List<Booking> findAll();
}

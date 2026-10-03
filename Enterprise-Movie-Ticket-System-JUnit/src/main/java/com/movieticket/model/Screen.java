package com.movieticket.model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Screen {

	private final String id;
	private final String name;
	private final Map<String, Seat> seats;
	
	/*List<Seat> screenSeats = Arrays.asList(new Seat("A1", 1, 1, SeatType.REGULAR, 200.0),
			new Seat("A2", 1, 2, SeatType.REGULAR, 200.0), new Seat("A3", 1, 3, SeatType.PREMIUM, 350.0));

	Screen screen = new Screen("SCR-1", "IMAX Screen 1", screenSeats);*/

	public Screen(String id, String name, List<Seat> seatList) {
		super();
		this.id = id;
		this.name = name;
		this.seats = new ConcurrentHashMap<>();
		seatList.forEach(seat -> this.seats.put(seat.getSeatId(), seat));

	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Map<String, Seat> getSeats() {
		return seats;
	}

	@Override
	public String toString() {
		return "Screen [id=" + id + ", name=" + name + ", seats=" + seats + "]";
	}
	

}

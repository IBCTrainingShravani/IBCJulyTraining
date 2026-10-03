package com.movieticket.model;

import java.util.List;

public class Theatre {
	private final String id;
	private final String name;
	private final String city;
	private final String location;
	private final List<Screen> screens;
	
	/*List<Seat> screenSeats = Arrays.asList(new Seat("A1", 1, 1, SeatType.REGULAR, 200.0),
			new Seat("A2", 1, 2, SeatType.REGULAR, 200.0), new Seat("A3", 1, 3, SeatType.PREMIUM, 350.0));

	Screen screen = new Screen("SCR-1", "IMAX Screen 1", screenSeats);
	Theatre theatre = new Theatre("TH-1", "PVR Cinemas", "Bengaluru", "Koramangala",
			Collections.singletonList(screen));*/

	public Theatre(String id, String name, String city, String location, List<Screen> screens) {
		
		
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.location = location;
		this.screens = screens;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public String getLocation() {
		return location;
	}

	public List<Screen> getScreens() {
		return screens;
	}

	@Override
	public String toString() {
		return "Theatre [id=" + id + ", name=" + name + ", city=" + city + ", location=" + location + ", screens="
				+ screens + "]";
	}
	

}

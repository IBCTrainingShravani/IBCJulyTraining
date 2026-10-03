package com.movieticket.model;

import com.movieticket.enums.SeatType;

public class Seat {

	private final String seatId;
	private final int row;
	private final int column;
	private final SeatType seatType;

	private final double price;
	
	/*List<Seat> screenSeats = Arrays.asList(new Seat("A1", 1, 1, SeatType.REGULAR, 200.0),
	new Seat("A2", 1, 2, SeatType.REGULAR, 200.0), new Seat("A3", 1, 3, SeatType.PREMIUM, 350.0));

Screen screen = new Screen("SCR-1", "IMAX Screen 1", screenSeats);*/

	public Seat(String seatId, int row, int column, SeatType seatType, double price) {
		super();
		this.seatId = seatId;
		this.row = row;
		this.column = column;
		this.seatType = seatType;
		this.price = price;
	}

	public String getSeatId() {
		return seatId;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Seat [seatId=" + seatId + ", row=" + row + ", column=" + column + ", seatType=" + seatType + ", price="
				+ price + "]";
	}
	

}

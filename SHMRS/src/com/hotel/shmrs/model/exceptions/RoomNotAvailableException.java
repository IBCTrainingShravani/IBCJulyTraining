package com.hotel.shmrs.model.exceptions;

public class RoomNotAvailableException extends Exception {
	public RoomNotAvailableException(String message) {
		super(message);
	}
}
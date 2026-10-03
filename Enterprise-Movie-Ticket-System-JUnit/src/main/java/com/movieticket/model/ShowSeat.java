package com.movieticket.model;

import java.time.Duration;
import java.time.LocalDateTime;

import com.movieticket.enums.SeatStatus;
import com.movieticket.enums.SeatType;

public class ShowSeat {

	private final String seatId;
	private final SeatType seatType;
	private final double price;
	private SeatStatus status;
	private String lockedByUserId;
	private LocalDateTime lockTimestamp;

	public ShowSeat(String seatId, SeatType seatType, double price) {

		this.seatId = seatId;
		this.seatType = seatType;
		this.price = price;
		this.status = SeatStatus.AVAILABLE;
	}

	public String getSeatId() {
		return seatId;
	}

	public SeatStatus getStatus() {
		return status;
	}
	public double getPrice() {
		return price;
	}

	public boolean isLockExpired(int lockTimeoutMinutes) {
		if (status != SeatStatus.LOCKED || lockTimestamp == null)
			return false;
		return Duration.between(lockTimestamp, LocalDateTime.now()).toMinutes() >= lockTimeoutMinutes;

	}

	public void lockSeat(String userId) {
		this.status = SeatStatus.LOCKED;
		this.lockedByUserId = userId;
		this.lockTimestamp = null;
	}

	public void releaseLock() {
		this.status = SeatStatus.AVAILABLE;
		this.lockedByUserId = null;
		this.lockTimestamp = null;

	}

	public void setStatus(SeatStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ShowSeat [seatId=" + seatId + ", seatType=" + seatType + ", price=" + price + ", status=" + status
				+ ", lockedByUserId=" + lockedByUserId + ", lockTimestamp=" + lockTimestamp + "]";
	}
	

}

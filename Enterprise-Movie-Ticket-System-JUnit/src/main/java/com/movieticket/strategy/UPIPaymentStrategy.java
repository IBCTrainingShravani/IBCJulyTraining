package com.movieticket.strategy;

public class UPIPaymentStrategy implements PaymentStrategy {

	public boolean processPayment(String bookingId, double amount) {
		System.out.println("[UPI Gateway]processed payment" + amount + "for booking:" + bookingId);
		return true;
	}

	public boolean processRefund(String bookingId, double amount) {
		System.out.println("UPI processed refund " + amount + "for Booking:" + bookingId);
		return true;
	}
}

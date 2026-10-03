package com.movieticket.strategy;

public class NetBankingPaymentStrategy implements PaymentStrategy {

	@Override
	public boolean processPayment(String bookingId, double amount) {
		// TODO Auto-generated method stub
		System.out.println("NetBanking processed payment:" + amount + "for booking:" + bookingId);

		return true;
	}

	@Override
	public boolean processRefund(String bookingId, double amount) {
		// TODO Auto-generated method stub
		System.out.println("NetBanking processed payment:" + amount + "for booking:" + bookingId);

		return true;
	}

}

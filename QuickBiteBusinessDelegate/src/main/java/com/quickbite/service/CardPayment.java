package com.quickbite.service;

import com.quickbite.exception.PaymentFailedException;

public class CardPayment implements PaymentStrategy {
	private String cardNumber;

	public CardPayment(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Override
	public boolean processPayment(double amount) throws PaymentFailedException {
		System.out.println("CArd Ending" + cardNumber.substring(cardNumber.length() - 4));

		System.out.println("Card successfully processed payment:" + amount);
		// TODO Auto-generated method stub
		return true;
	}

}

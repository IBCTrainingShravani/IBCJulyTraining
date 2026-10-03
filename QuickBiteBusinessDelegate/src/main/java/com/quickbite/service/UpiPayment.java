package com.quickbite.service;

import com.quickbite.exception.PaymentFailedException;

public class UpiPayment implements PaymentStrategy {
	private String upiId;

	public UpiPayment(String upiId) {
		this.upiId = upiId;

	}

	public boolean processPayment(double amount) throws PaymentFailedException {
		System.out.println("UPI :" + upiId + "....");
		if (amount > 10000) {
			throw new PaymentFailedException("UPI Transaction limilt exeeds");
		}
		System.out.println("UPI successfully debited" + amount + "via UPI");
		return true;
	}

}

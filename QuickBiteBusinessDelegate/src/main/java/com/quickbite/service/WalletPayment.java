package com.quickbite.service;

import com.quickbite.exception.PaymentFailedException;
import com.quickbite.model.Wallet;

public class WalletPayment implements PaymentStrategy {
	private Wallet wallet;

	public WalletPayment(Wallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public boolean processPayment(double amount) throws PaymentFailedException {

		System.out.println("Wallet balance..");
		if (!wallet.debit(amount)) {
			throw new PaymentFailedException("Insufficient Balance" + wallet.getBalance());
		}

		System.out.println("Debited:" + amount + "remaining balance" + wallet.getBalance());
		// TODO Auto-generated method stub
		return true;
	}

}

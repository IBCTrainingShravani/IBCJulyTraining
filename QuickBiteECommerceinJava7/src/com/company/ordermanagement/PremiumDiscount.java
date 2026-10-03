package com.company.ordermanagement;

public class PremiumDiscount implements DiscountStrategy {

	@Override
	public double calculateDiscount(double amount) {
		// TODO Auto-generated method stub
		return amount * 0.15;
	}

	

}

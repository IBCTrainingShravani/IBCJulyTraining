package com.company.ordermanagement;

public class RegularDiscount implements DiscountStrategy {

	@Override
	public double calculateDiscount(double amount) {
		// TODO Auto-generated method stub
		return amount*0.05;
	}

}

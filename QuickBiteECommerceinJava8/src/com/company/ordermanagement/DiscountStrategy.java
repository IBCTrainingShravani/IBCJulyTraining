package com.company.ordermanagement;

public interface DiscountStrategy {// functional interface

	double calculateDiscount(double amount);

	default void printDiscountNotice(double originalAmount, double discount) {
		System.out.println("Original: $" + originalAmount + " | Discount: $" + discount + " | Net: $"
				+ (originalAmount - discount));
	}
}

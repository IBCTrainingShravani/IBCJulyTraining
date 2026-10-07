package com.hotel.shmrs.strategy;

import com.hotel.shmrs.model.enums.PaymentMode;
import com.hotel.shmrs.model.exceptions.InvalidPaymentException;

public interface PaymentStrategy {
	boolean executePayment(double amount) throws InvalidPaymentException;

	PaymentMode getMode();
}

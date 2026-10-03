package com.quickbite.presentation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.quickbite.config.HibernateConfig;
import com.quickbite.delegate.QuickBiteBusinessDelegate;
import com.quickbite.dto.CustomerDTO;

public class QuickBiteApp {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HibernateConfig.class);
		QuickBiteBusinessDelegate delegate = ctx.getBean(QuickBiteBusinessDelegate.class);
		//public CustomerDTO(int id, String name, String email, long mobile, String address, double initialWallet)
		CustomerDTO dto = new CustomerDTO(1, "Swati", "swati@gmail.com", 7492723968L, "Hyderabad", 60000.00);

		delegate.registerCustomer(dto);
		System.out.println("Customer Saved successfully");
		ctx.close();
	}
}

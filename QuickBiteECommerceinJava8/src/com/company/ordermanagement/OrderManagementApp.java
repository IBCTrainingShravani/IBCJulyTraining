package com.company.ordermanagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import java.util.Arrays;

public class OrderManagementApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		Customer john = new Customer(1, "John", "Hyderabad", true);
		Customer smith = new Customer(2, "SMith", "Bangalore", true);
		Customer david = new Customer(3, "David", "Chennai", false);

		List<Order> orders = Arrays.asList(
				new Order(101, john, 45000.00, "Electronics", LocalDate.parse("2025-01-10", formatter)),

				new Order(102, david, 5000.00, "Books", LocalDate.parse("2025-02-10", formatter)),
				new Order(103, smith, 50000.00, "Fashion", LocalDate.parse("2025-03-10", formatter)),
				new Order(101, john, 40000.00, "Electronics", LocalDate.parse("2025-04-10", formatter)));

		ReportService reportService = new ReportService();
		System.out.println("====1. Electronics Orders Java 8===");

		reportService.getElectronicsOrders(orders).forEach(System.out::println);

		System.out.println("\n===2.High value orders==");

		reportService.getHighValueOrders(orders, 10000.0).forEach(System.out::println);

		System.out.println("\n===3.Total revenue==");

		System.out.println("Total revenue:" + reportService.getTotalRevenue(orders));

		System.out.println("\n==4.Category count report==");
		Map<String, Long> categoryCounts = reportService.categoryReport(orders);

		categoryCounts.forEach((category, count) -> System.out.println(category + "->" + count + "orders"));

		System.out.println("\n==5.Partotioning orders==");

		Map<Boolean, List<Order>> partitioned = reportService.partitionByPremiumStatus(orders);

		System.out.println("Regular/Guest Orders Count:" + partitioned.get(false).size());

		System.out.println("\n===6.Discount calculations==");

		DiscountStrategy premiumDiscount = amount -> amount * 0.15;

		DiscountStrategy regularDiscount = amount -> amount * 0.05;

		orders.forEach(order -> {
			boolean isPremium = order.getCustomer().map(Customer::isPremium).orElse(false);

			DiscountStrategy strategy = isPremium ? premiumDiscount : regularDiscount;

			double discount = strategy.calculateDiscount(order.getAmount());

			String customerName = order.getCustomer().map(Customer::getName).orElse("Guest");

			System.out.println("Order" + order.getOrderId() + "(" + customerName + ")");

			strategy.printDiscountNotice(order.getAmount(), discount);

		});

	}

}

package com.company.ordermanagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderManagementApp {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Customer john = new Customer(1, "JOHN", "Hyderabad", true);
		Customer smith = new Customer(2, "SMITH", "Bangalore", true);
		Customer david = new Customer(3, "David", "Chennai", false);

		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(101, john, 45000.00, "Electronics", sdf.parse("2025-01-10")));
		orders.add(new Order(102, david, 5000.00, "Books", sdf.parse("2025-02-10")));
		orders.add(new Order(103, smith, 50000.00, "Fashion", sdf.parse("2025-03-15")));
		orders.add(new Order(101, john, 40000.00, "Electronics", sdf.parse("2025-04-10")));

		ReportService reportService = new ReportService();
		System.out.println("===1.ELectronics orders ===");

		List<Order> electronics = reportService.getElectronicsOrders(orders);
		for (Order o : electronics) {
			System.out.println(o);
		}
		System.out.println("\n==2.High Value Oredr(>10000)");

		List<Order> highValue = reportService.getHighValueOrders(orders, 10000.00);
		for (Order o : highValue) {
			System.out.println(o);
		}

		System.out.println("\n===3.Total revenue==");
		double totalRevenue = reportService.getTotalRevenue(orders);
		System.out.println("Total Slaes Revenue:" + totalRevenue);

		System.out.println("\n==4.Category count report ==");
		Map<String, Integer> categoryCounts = reportService.categoryReport(orders);
		for (String category : categoryCounts.keySet()) {
			System.out.println(category + "->" + categoryCounts.get(category) + "orders");

			/*Fashion->1orders
			Electronics->2orders
			Books->1orders*/

		}

		System.out.println("\n==5.Discount calculations ==");

		DiscountStrategy premiumStrategy = new PremiumDiscount();
		DiscountStrategy regularStrategy = new RegularDiscount();
		
		/*orders.add(new Order(101, john, 45000.00, "Electronics", sdf.parse("2025-01-10")));
		orders.add(new Order(102, david, 5000.00, "Books", sdf.parse("2025-02-10")));
		orders.add(new Order(103, smith, 50000.00, "Fashion", sdf.parse("2025-03-15")));
		orders.add(new Order(101, john, 40000.00, "Electronics", sdf.parse("2025-04-10")));
		
		*Customer john = new Customer(1, "JOHN", "Hyderabad", true);
		Customer smith = new Customer(2, "SMITH", "Bangalore", true);
		Customer david = new Customer(3, "David", "Chennai", false);*/


		for (Order o : orders) {
			double discount = 0;
			if (o.getCustomer() != null && o.getCustomer().isPremium()) {
				discount = premiumStrategy.calculateDiscount(o.getAmount());

			} else {
				discount = regularStrategy.calculateDiscount(o.getAmount());

			}

			System.out.println("Orders#" + o.getOrderId() + "(" + o.getCustomer().getName() + ")-Amount:"
					+ o.getAmount() + "|Applied Discount:" + discount);
		}

	}

}

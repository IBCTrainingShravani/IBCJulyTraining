package com.company.ordermanagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {
	
	/*public List<Order> getElectronicsOrders(List<Order> orders) {
		List<Order> result = new ArrayList<Order>();
		for (Order order : orders) {
			if ("Electronics".equals(order.getCategory())) {
				result.add(order);
			}
		}
		return result;

	}*/

	public List<Order> getElectronicsOrders(List<Order> orders) {
		return orders.stream().filter(order -> "Electronics".equalsIgnoreCase(order.getCategory()))
				.collect(Collectors.toList());
	}
	
	//reportService.getHighValueOrders(orders, 10000.0).forEach(System.out::println);
	
	/*public List<Order> getHighValueOrders(List<Order> orders, double minAmount) {
		List<Order> result = new ArrayList<Order>();
		for (Order order : orders) {
			if (order.getAmount() > minAmount) {
				result.add(order);
			}
		}
		return result;
	}*/

	public List<Order> getHighValueOrders(List<Order> orders, double minAmount) {
		return orders.stream().filter(order -> order.getAmount() > minAmount).collect(Collectors.toList());
	}
	
	/*public double getTotalRevenue(List<Order> orders) {
		double total = 0;
		for (Order order : orders) {
			total += order.getAmount();
		}
		return total;
	}*/

	public double getTotalRevenue(List<Order> orders) {
		return orders.stream().mapToDouble(Order::getAmount).sum();
	}
	
	/*public Map<String, Integer> categoryReport(List<Order> orders) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (Order order : orders) {
			String category = order.getCategory();
			System.out.println("category"+category);
			
			Integer count = map.get(category);
			
			/*Fashion->1orders
			Electronics->2orders
			Books->1orders*/

			/*if (count == null) {
				map.put(category, 1);
			}else {
				map.put(category, count + 1);
			}
		}
		return map;
	}*/

	public Map<String, Long> categoryReport(List<Order> orders) {
		return orders.stream().collect(Collectors.groupingBy(Order::getCategory, Collectors.counting()));
	}

	/*
	Customer john = new Customer(1, "John", "Hyderabad", true);
	Customer smith = new Customer(2, "SMith", "Bangalore", true);
	Customer david = new Customer(3, "David", "Chennai", false);

	List<Order> orders = Arrays.asList(
			new Order(101, john, 45000.00, "Electronics", LocalDate.parse("2025-01-10", formatter)),

			new Order(102, david, 5000.00, "Books", LocalDate.parse("2025-02-10", formatter)),
			new Order(103, smith, 50000.00, "Fashion", LocalDate.parse("2025-03-10", formatter)),
			new Order(101, john, 40000.00, "Electronics", LocalDate.parse("2025-04-10", formatter)));*/
	
	public Map<Boolean, List<Order>> partitionByPremiumStatus(List<Order> orders) {
		return orders.stream().collect(
				Collectors.partitioningBy(order -> order.getCustomer().map(Customer::isPremium).orElse(false)));
	}
	
	/*public List<Order> premiumOrders(List<Order> orders) {
		List<Order> result = new ArrayList<Order>();
		for (Order order : orders) {
			if (order.getCustomer() != null && order.getCustomer().isPremium()) {
				result.add(order);
			}
		}
		return result;
	}*/

	
	public List<Order> getPremiumOrders(List<Order> orders) {
		return orders.stream().filter(order -> order.getCustomer().map(Customer::isPremium).orElse(false))
				.collect(Collectors.toList());
	}
}

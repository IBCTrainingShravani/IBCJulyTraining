package com.company.ordermanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {
	
	

	public List<Order> getElectronicsOrders(List<Order> orders) {
		List<Order> result = new ArrayList<Order>();
		for (Order order : orders) {
			if ("Electronics".equals(order.getCategory())) {
				result.add(order);
			}
		}
		return result;

	}

	public List<Order> getHighValueOrders(List<Order> orders, double minAmount) {
		List<Order> result = new ArrayList<Order>();
		for (Order order : orders) {
			if (order.getAmount() > minAmount) {
				result.add(order);
			}
		}
		return result;
	}
	
	/*orders.add(new Order(101, john, 45000.00, "Electronics", sdf.parse("2025-01-10")));
	orders.add(new Order(102, david, 5000.00, "Books", sdf.parse("2025-02-10")));
	orders.add(new Order(101, smith, 50000.00, "Fashion", sdf.parse("2025-03-15")));
	orders.add(new Order(101, john, 40000.00, "Electronics", sdf.parse("2025-04-10")));*/

	public double getTotalRevenue(List<Order> orders) {
		double total = 0;
		for (Order order : orders) {
			total += order.getAmount();
		}
		return total;
	}

	public Map<String, Integer> categoryReport(List<Order> orders) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (Order order : orders) {
			String category = order.getCategory();
			System.out.println("category"+category);
			
			Integer count = map.get(category);
			
			/*Fashion->1orders
			Electronics->2orders
			Books->1orders*/

			if (count == null) {
				map.put(category, 1);
			}else {
				map.put(category, count + 1);
			}
		}
		return map;
	}

	public List<Order> premiumOrders(List<Order> orders) {
		List<Order> result = new ArrayList<Order>();
		for (Order order : orders) {
			if (order.getCustomer() != null && order.getCustomer().isPremium()) {
				result.add(order);
			}
		}
		return result;
	}
}

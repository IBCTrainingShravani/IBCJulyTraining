package com.hotel.shmrs.model.entity;

import java.util.List;

public class Manager extends Employee {
	public Manager(String empId, String name, double salary) {
		super(empId, name, salary);
	}
	
	/*List<Employee> staffList = List.of(new Manager("EMP-01", "Vikram Rathore", 95000.0),
			new Receptionist("EMP-02", "Ananya Sen", 45000.0));
	staffList.forEach(Employee::performDuties);*/


	@Override
	public void performDuties() {
		System.out.println("[Staff: Manager " + name + "] Authorizing VIP allocations and inventory audits.");
	}
}

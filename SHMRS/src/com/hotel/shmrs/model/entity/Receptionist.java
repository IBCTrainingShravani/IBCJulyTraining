package com.hotel.shmrs.model.entity;

public class Receptionist extends Employee {
	public Receptionist(String empId, String name, double salary) {
		super(empId, name, salary);
	}
	
	/*List<Employee> staffList = List.of(new Manager("EMP-01", "Vikram Rathore", 95000.0),
	new Receptionist("EMP-02", "Ananya Sen", 45000.0));
staffList.forEach(Employee::performDuties);*/

	@Override
	public void performDuties() {
		System.out.println("[Staff: Receptionist " + name + "] Managing guest check-in terminal.");
	}
}

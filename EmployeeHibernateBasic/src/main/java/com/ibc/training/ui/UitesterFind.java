package com.ibc.training.ui;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ibc.training.entity.EmployeeEntity;
import com.ibc.training.util.HibernateUtil;

public class UitesterFind {
	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		EmployeeEntity employeeEntity = (EmployeeEntity) session.get(EmployeeEntity.class, 1002);
		if (employeeEntity != null) {
			System.out.println(employeeEntity.getEmployeeId() + employeeEntity.getEmployeeName()
					+ employeeEntity.getRole() + employeeEntity.getInsertTime() + employeeEntity.getSalary());

		} else {
			System.out.println("EMployee not found");
		}
		session.close();

		HibernateUtil.getSessionFactory().close();

	}

}

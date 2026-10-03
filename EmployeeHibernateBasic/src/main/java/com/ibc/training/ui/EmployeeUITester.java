package com.ibc.training.ui;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ibc.training.entity.EmployeeEntity;
import com.ibc.training.util.HibernateUtil;



public class EmployeeUITester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		EmployeeEntity employee = new EmployeeEntity();
		employee.setEmployeeId(1004);

		employee.setEmployeeName("Mohapatra");
		employee.setInsertTime(new Date());
		employee.setRole("Java Developer");
		employee.setSalary(800000.0);

		session.beginTransaction();
		session.persist(employee);

		session.getTransaction().commit();
		System.out.println("EMployee Registered successfully");
		session.close();

		HibernateUtil.getSessionFactory().close();

	}

}

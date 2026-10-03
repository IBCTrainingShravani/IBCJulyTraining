package com.ibc.training.ui;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ibc.training.entity.EmployeeEntity;
import com.ibc.training.util.HibernateUtil;

public class UitesterDelete {
	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		EmployeeEntity employee = (EmployeeEntity) session.get(EmployeeEntity.class, 1002);
		if (employee != null) {
			session.beginTransaction();

			session.remove(employee);

			session.getTransaction().commit();
			System.out.println("Employee deleted successfully");

		} else {
			System.out.println("not found");
		}
		session.close();

		HibernateUtil.getSessionFactory().close();
	}

}

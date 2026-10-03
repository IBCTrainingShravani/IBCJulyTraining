package com.ibc.training.ui;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ibc.training.entity.EmployeeEntity;
import com.ibc.training.util.HibernateUtil;

public class UitesterUpdate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sf=HibernateUtil.getSessionFactory();
		Session session=sf.openSession();
		EmployeeEntity employee=(EmployeeEntity)session.get(EmployeeEntity.class,1003);
		
		if(employee!=null) {
			session.beginTransaction();
			employee.setSalary(900000.0);
			session.merge(employee);
			
			session.getTransaction().commit();
			System.out.println("Employee updated successfully");
			
		}else {
			System.out.println("Not found");
		}
		session.close();
		

		HibernateUtil.getSessionFactory().close();
	}

}

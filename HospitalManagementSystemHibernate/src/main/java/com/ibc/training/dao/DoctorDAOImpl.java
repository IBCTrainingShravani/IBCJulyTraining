package com.ibc.training.dao;

import java.util.List;

import javax.transaction.SystemException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ibc.training.entity.Doctor;
import com.ibc.training.exception.DaoException;
import com.ibc.training.util.HibernateUtil;



public class DoctorDAOImpl implements GenericDAO<Doctor> {

	@Override
	public void save(Doctor doctor) throws DaoException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = (Transaction) session.beginTransaction();
			session.persist(doctor);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				try {
					tx.rollback();
				} catch (IllegalStateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			throw new DaoException(null, e);
		} finally {
			if (session != null)
				session.close();
		}

	}

	@Override
	public Doctor findById(Long id) throws DaoException {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Doctor doctor = (Doctor) session.get(Doctor.class, id);
			if (doctor != null) {
				doctor.getPatients().size();
			}
			return doctor;
		} catch (Exception e) {
			
			throw new DaoException("FindById failed", e);

		} finally {
			if (session != null) {
				session.close(); 
			}

		}
	}

	@Override
	public List<Doctor> findAll() throws DaoException {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			List<Doctor> doctor = session.createQuery("from Doctor", Doctor.class).list();
			return doctor;
		} catch (Exception e) {
			// TODO: handle exception
			throw new DaoException("FindAll failed", e);

		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void update(Doctor doctor) throws DaoException {
		// TODO Auto-generated method stub
		Session session = null;

		Transaction tx = null;

		try {

			session = HibernateUtil.getSessionFactory().openSession();

			tx = (Transaction) session.beginTransaction();

			session.persist(doctor);

			tx.commit();

		} catch (Exception e) {

			if (tx != null)
				try {
					tx.rollback();
				} catch (IllegalStateException e1) {
					
					e1.printStackTrace();
				}

			throw new DaoException("Update failed", e);

		} finally {

			if (session != null) {

				session.close();

			}

		}

	}

	@Override
	public void delete(Long id) throws DaoException {
		// TODO Auto-generated method stub

		Session session = null;

		Transaction tx = null;

		try {

			session = HibernateUtil.getSessionFactory().openSession();

			tx = (Transaction) session.beginTransaction();

			Doctor doctor = (Doctor) session.get(Doctor.class, id);

			if (doctor != null) {

				session.remove(doctor);

			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null)
				try {
					tx.rollback();
				} catch (IllegalStateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			throw new DaoException("Delete failed", e);

		} finally {

			if (session != null)
				session.close();

		}

	}

}
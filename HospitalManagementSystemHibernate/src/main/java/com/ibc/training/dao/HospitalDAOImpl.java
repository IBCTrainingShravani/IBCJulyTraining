package com.ibc.training.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ibc.training.entity.Hospital;
import com.ibc.training.exception.DaoException;
import com.ibc.training.util.HibernateUtil;

public class HospitalDAOImpl implements GenericDAO<Hospital> {

	@Override
	public void save(Hospital hospital) throws DaoException {
		// TODO Auto-generated method stub
		Session session = null;

		Transaction tx = null;

		try {

			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();

			session.persist(hospital);

			tx.commit();

		} catch (Exception e) {

			if (tx != null)
				tx.rollback();

			throw new DaoException("Save failed", e);

		} finally {

			if (session != null)
				session.close(); 

		}

	}

	@Override
	public Hospital findById(Long id) throws DaoException {
		// TODO Auto-generated method stub
		Session session = null;
		Hospital hospital =null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			 hospital = (Hospital) session.get(Hospital.class, id);
			
			if (hospital != null) {
				hospital.getDoctors().size();
				
			
		}
		}catch (Exception e) {
			throw new DaoException("FindById failed", e);
		} finally {
			if (session != null) {
				session.close(); 
			}
		}
		return hospital;
	}

	@Override
	public List<Hospital> findAll() throws DaoException {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			List<Hospital> hospitals = session.createQuery("from Hospital",Hospital.class).list();
			return hospitals;
		} catch (Exception e) {
			throw new DaoException("FindAll failed", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void update(Hospital hospital) throws DaoException {
		// TODO Auto-generated method stub
		Session session = null;

		Transaction tx = null;

		try {

			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();

			session.persist(hospital);

			tx.commit();

		} catch (Exception e) {

			if (tx != null)
				tx.rollback();

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

			tx = session.beginTransaction();

			Hospital hospital = (Hospital) session.get(Hospital.class, id);

			if (hospital != null) {

				session.remove(hospital);

			}

			tx.commit();

		} catch (Exception e) {

			if (tx != null)
				tx.rollback();

			throw new DaoException("Delete failed", e);

		} finally {

			if (session != null)
				session.close();

		}

	}

}

package com.ibc.training.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ibc.training.entity.Patient;
import com.ibc.training.exception.DaoException;
import com.ibc.training.util.HibernateUtil;

public class PatientDAOImpl implements GenericDAO<Patient> {

	@Override
	public void save(Patient patient) throws DaoException {
		// TODO Auto-generated method stub
		Session session = null;

		Transaction tx = null;

		try {

			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();

			session.persist(patient);

			tx.commit();

		} catch (Exception e) {

			if (tx != null)
				tx.rollback();

			throw new DaoException("Save failed", e);

		} finally {

			if (session != null)
				session.close(); // Ensure session is always closed

		}

	}

	@Override
	public Patient findById(Long id) throws DaoException {
		// TODO Auto-generated method stub
		Session session = null;
		Patient patient = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			 patient = (Patient) session.get(Patient.class, id);
			
			if (patient != null) {
				patient.getDoctors().size(); 
				
		
		}
		}catch (Exception e) {
			throw new DaoException("FindById failed", e);
		} finally {
			if (session != null) {
				session.close(); 
			}
		}
		return patient;
	}

	@Override
	public List<Patient> findAll() throws DaoException {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			List<Patient> patients = session.createQuery("from Patient",Patient.class).list();
			return patients;
		} catch (Exception e) {
			throw new DaoException("FindAll failed", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void update(Patient patient) throws DaoException {
		// TODO Auto-generated method stub
		Session session = null;

		Transaction tx = null;

		try {

			session = HibernateUtil.getSessionFactory().openSession();

			tx = session.beginTransaction();

			session.persist(patient);

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

			Patient patient = (Patient) session.get(Patient.class, id);

			if (patient != null) {

				session.remove(patient);

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
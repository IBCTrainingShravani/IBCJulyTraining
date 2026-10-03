package com.ibc.training.service;

import java.util.List;

import com.ibc.training.dao.GenericDAO;
import com.ibc.training.dao.PatientDAOImpl;
import com.ibc.training.entity.Patient;
import com.ibc.training.exception.DaoException;
import com.ibc.training.exception.ServiceException;

public class PatientServiceImpl implements PatientService {

	private GenericDAO<Patient> dao = new PatientDAOImpl();

	@Override
	public void registerPatient(Patient patient) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			dao.save(patient);
		} catch (DaoException e) {

			throw new ServiceException("Create error", e);
		}

	}

	@Override
	public Patient getPatient(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return dao.findById(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Read error", e);
		}

	}

	@Override
	public List<Patient> getAllPatients() throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return dao.findAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Read error", e);
		}
	}

	@Override
	public void updatePatient(Patient patient) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			dao.update(patient);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Update error", e);

		}

	}

	@Override
	public void deletePatient(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			dao.delete(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Delete error", e);

		}

	}

}

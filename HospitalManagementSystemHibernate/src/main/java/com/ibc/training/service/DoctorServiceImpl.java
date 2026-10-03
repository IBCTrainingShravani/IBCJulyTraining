package com.ibc.training.service;

import java.util.List;

import com.ibc.training.dao.DoctorDAOImpl;
import com.ibc.training.dao.GenericDAO;
import com.ibc.training.entity.Doctor;
import com.ibc.training.exception.DaoException;
import com.ibc.training.exception.ServiceException;

public class DoctorServiceImpl implements DoctorService {

	private GenericDAO<Doctor> dao = new DoctorDAOImpl();

	@Override
	public void addDoctor(Doctor doctor) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			dao.save(doctor);
		} catch (DaoException e) {

			throw new ServiceException("Create error", e);
		}

	}

	@Override
	public Doctor getDoctor(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return dao.findById(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Read error", e);
		}
	}

	@Override
	public List<Doctor> getAllDoctors() throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return dao.findAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Read error", e);
		}
	}

	@Override
	public void updateDoctor(Doctor doctor) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			dao.update(doctor);
		} catch (DaoException e) {
			
			throw new ServiceException("Update error", e);

		}

	}

	@Override
	public void deleteDoctor(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			dao.delete(id);
		} catch (DaoException e) {
			
			throw new ServiceException("Delete error", e);

		}

	}

}
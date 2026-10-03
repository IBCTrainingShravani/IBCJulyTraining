package com.ibc.training.service;

import java.util.List;

import com.ibc.training.dao.GenericDAO;
import com.ibc.training.dao.HospitalDAOImpl;
import com.ibc.training.entity.Hospital;
import com.ibc.training.exception.DaoException;
import com.ibc.training.exception.ServiceException;

public class HospitalServiceImpl implements HospitalService {

	private GenericDAO<Hospital> dao = new HospitalDAOImpl();

	@Override
	public void addHospital(Hospital hospital) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			dao.save(hospital);
		} catch (DaoException e) {

			throw new ServiceException("Create error", e);
		}

	}

	@Override
	public Hospital getHospital(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return dao.findById(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Read error", e);
		}
	}

	@Override
	public List<Hospital> getAllHospitals() throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return dao.findAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Read error", e);
		}
	}

	@Override
	public void updateHospital(Hospital hospital) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			dao.update(hospital);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Update error", e);

		}

	}

	@Override
	public void deleteHospital(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			dao.delete(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Delete error", e);

		}

	}

}

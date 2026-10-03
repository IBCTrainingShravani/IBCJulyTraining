package com.ibc.training.service;

import java.util.List;

import com.ibc.training.entity.Hospital;
import com.ibc.training.exception.ServiceException;

public interface HospitalService {

	void addHospital(Hospital hospital) throws ServiceException;

	Hospital getHospital(Long id) throws ServiceException;

	List<Hospital> getAllHospitals() throws ServiceException;

	void updateHospital(Hospital hospital) throws ServiceException;

	void deleteHospital(Long id) throws ServiceException;
}

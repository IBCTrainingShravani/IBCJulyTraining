package com.ibc.training.service;

import java.util.List;

import com.ibc.training.entity.Patient;
import com.ibc.training.exception.ServiceException;

public interface PatientService {

	void registerPatient(Patient patient) throws ServiceException;

	Patient getPatient(Long id) throws ServiceException;

	List<Patient> getAllPatients() throws ServiceException;

	void updatePatient(Patient patient) throws ServiceException;

	void deletePatient(Long id) throws ServiceException;

}

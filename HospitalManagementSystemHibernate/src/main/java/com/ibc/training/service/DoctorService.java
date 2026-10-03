package com.ibc.training.service;

import java.util.List;

import com.ibc.training.entity.Doctor;
import com.ibc.training.exception.ServiceException;

public interface DoctorService {
	void addDoctor(Doctor doctor) throws ServiceException;

	Doctor getDoctor(Long id) throws ServiceException;

	List<Doctor> getAllDoctors() throws ServiceException;

	void updateDoctor(Doctor doctor) throws ServiceException;

	void deleteDoctor(Long id) throws ServiceException;

}

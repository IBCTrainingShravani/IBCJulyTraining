package com.ibc.training.dao;

import java.util.List;

import com.ibc.training.exception.DaoException;

public interface GenericDAO<T> {
	void save(T entity) throws DaoException;

	T findById(Long id) throws DaoException;

	List<T> findAll() throws DaoException;

	void update(T entity) throws DaoException;

	void delete(Long id) throws DaoException;

}

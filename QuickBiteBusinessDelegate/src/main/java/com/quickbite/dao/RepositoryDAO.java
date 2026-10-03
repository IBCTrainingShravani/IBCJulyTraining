package com.quickbite.dao;

import java.util.List;

public interface RepositoryDAO<T> {
	void save(int id, T entity);

	T findById(int id);

	List<T> findAll();
}

package com.quickbite.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.quickbite.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Customer customer) {

        sessionFactory
                .getCurrentSession()
                .persist(customer);
    }

    @Override
    public Customer findById(int id) {

        return sessionFactory
                .getCurrentSession()
                .get(Customer.class, id);
    }

    @Override
    public List<Customer> findAll() {

        return sessionFactory
                .getCurrentSession()
                .createQuery(
                     "from Customer",
                     Customer.class)
                .list();
    }

    @Override
    public Customer findByEmail(String email) {

        String hql =
                "from Customer where email=:email";

        return sessionFactory
                .getCurrentSession()
                .createQuery(hql, Customer.class)
                .setParameter("email", email)
                .uniqueResult();
    }
}
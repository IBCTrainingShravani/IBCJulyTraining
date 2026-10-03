package com.quickbite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quickbite.dao.CustomerDAO;
import com.quickbite.dto.CustomerDTO;
import com.quickbite.exception.CustomerNotFoundException;
import com.quickbite.model.Customer;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    public Customer registerCustomer(
             CustomerDTO dto) {

        Customer customer =
                new Customer();

        customer.setId(dto.id);
        customer.setName(dto.name);
        customer.setEmail(dto.email);
        customer.setMobile(dto.mobile);
        customer.setAddress(dto.address);
        customer.setWalletBalance(
                dto.initialWallet);

        customerDAO.save(customer);

        return customer;
    }

    public Customer getCustomer(int id)
            throws CustomerNotFoundException {

        Customer customer =
                customerDAO.findById(id);

        if(customer == null)
            throw new CustomerNotFoundException(
                    "Customer not found");

        return customer;
    }
}

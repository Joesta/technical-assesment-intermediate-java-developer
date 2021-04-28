package com.github.joesta.cruddemo.service;

import com.github.joesta.cruddemo.exceptions.CustomerException;
import com.github.joesta.cruddemo.models.Customer;
import com.github.joesta.cruddemo.repository.CustomerRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class CustomerService {

    private final Log log = LogFactory.getLog(this.getClass().getName());

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) throws CustomerException {
        log.info("saveCustomer(): inserting customer: " + customer.getCustomerNumber() + " from CustomerService");
        if (customerRepository.existsById(customer.getCustomerNumber()))
            throw new CustomerException("Customer already exist");

        return customerRepository.insert(customer);
    }


    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> findByCustomerNumber(String id) {
        log.info("findById() running... getting user with customerNumber " + id);
        return customerRepository.findById(id);
    }

    public void deleteAll() {
        log.info("deleteAll(): in customer service");
        customerRepository.deleteAll();
    }
}

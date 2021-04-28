package com.github.joesta.cruddemo.service;

import com.github.joesta.cruddemo.exceptions.CustomerException;
import com.github.joesta.cruddemo.models.Customer;
import com.github.joesta.cruddemo.repository.CustomerRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final Log log = LogFactory.getLog(this.getClass().getName());

    @Autowired
    private CustomerRepository customerRepository;

    /**
     *  create a new customer
     * @param customer a customer to be created
     * @return created customer
     * @throws CustomerException Throws customerException if already exist
     */
    public Customer saveCustomer(Customer customer) throws CustomerException {
        log.info("saveCustomer(): inserting customer: " + customer.getCustomerNumber() + " from CustomerService");
        if (customerRepository.existsById(customer.getCustomerNumber()))
            throw new CustomerException("Customer already exist");

        return customerRepository.insert(customer);
    }

    /**
     *  Update a customer record
     * @param customer A customer to update
     * @return Updated customer
     */
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     *  find a customer associated with the unique customer number
     * @param customerNumber customer number
     * @return customer that is found with the customer number
     */
    public Optional<Customer> findByCustomerNumber(String customerNumber) {
        log.info("findById() running... getting user with customerNumber " + customerNumber);
        return customerRepository.findById(customerNumber);
    }

    /**
     *  delete a customer associated with the unique customer number
     * @param customerNumber Unique customer number used as ID
     * @return deleted customer
     */
    public Optional<Customer> deleteCustomer(String customerNumber) {
        Optional<Customer> targetCustomer = customerRepository.findById(customerNumber);
        if (targetCustomer.isPresent())
            customerRepository.deleteById(customerNumber);
        return targetCustomer;

    }

    /**
     *  deletes all customers
     */
    public void deleteAll() {
        log.info("deleteAll(): in customer service");
        customerRepository.deleteAll();
    }
}

package com.github.joesta.cruddemo.service;

import com.github.joesta.cruddemo.CrudDemoApplicationTests;
import com.github.joesta.cruddemo.exceptions.CustomerException;
import com.github.joesta.cruddemo.models.Customer;
import com.github.joesta.cruddemo.models.CustomerBuilder;
import com.github.joesta.cruddemo.repository.CustomerRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CustomerServiceTest extends CrudDemoApplicationTests {

    private final Log log = LogFactory.getLog(this.getClass().getName());

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    private List<Customer> customers;

    @Before
    public void setUp() {
        log.info("setUp() running");
        customer = CustomerBuilder.buildACustomer(1);
        customers = CustomerBuilder.buildCustomers(5);
    }

    @After
    public void tearDown() {
        log.info("tearDown() running");
        customerService.deleteAll();
    }

    @Test
    public void givenCustomerToSaveThenShouldReturnSavedCustomer() throws CustomerException {
        log.info("givenCustomerToCreateThenShouldReturnCreatedCustomer() running in CustomerServiceTest");
        customerService.saveCustomer(customer);

        Optional<Customer> c = customerService.findByCustomerNumber(customer.getCustomerNumber());
        Assertions.assertTrue(c.isPresent());
        Assertions.assertEquals(customer.getCustomerNumber(), c.get().getCustomerNumber());
    }

    @Test
    public void givenCustomerToUpdateThenShouldReturnSavedCustomer() {
        log.info("givenCustomerToUpdateThenShouldReturnSavedCustomer() running in CustomerServiceTest");
        Customer targetCustomer = CustomerBuilder.buildACustomer(1);
        targetCustomer.setFirstName("John");
        targetCustomer.setLastName("Doe");

        Customer expectedCustomer = customerService.updateCustomer(targetCustomer);
        log.info("updated customer object " + expectedCustomer);
        Assertions.assertNotNull(expectedCustomer);
        Assertions.assertNotEquals(expectedCustomer, customer);
    }

    @Test
    public void givenTheCustomerNumberShouldDeleteTheAssociatedCustomer() {
        log.info("givenTheCustomerNumberShouldDeleteTheAssociatedCustomer() running in CustomerServiceTest");
        Optional<Customer> deletedCustomer = customerService.deleteCustomer(customer.getCustomerNumber());
        Assertions.assertFalse(deletedCustomer.isPresent());
    }

    @Test
    public void givenCustomerNumberToFindShouldReturnCustomer() throws CustomerException {
        Customer customer = CustomerBuilder.buildACustomer(1);
        customerService.saveCustomer(customer);
        log.info("givenCustomerNumberToFindShouldReturnCustomer() running in CustomerServiceTest");
        Optional<Customer> foundCustomer =  customerService.findByCustomerNumber(customer.getCustomerNumber());
        log.info(foundCustomer);
        Assertions.assertTrue(foundCustomer.isPresent());
    }

    @Test
    public void shouldReturnAListOfCustomers() {
        customerRepository.saveAll(customers);
        log.info("shouldReturnAListOfCustomers() running in CustomerServiceTest");
        List<Customer> customers = customerService.findAllCustomers();
        log.info(customers);
        Assertions.assertNotNull(customers);
        Assertions.assertNotEquals(0,customers.size());
    }

}
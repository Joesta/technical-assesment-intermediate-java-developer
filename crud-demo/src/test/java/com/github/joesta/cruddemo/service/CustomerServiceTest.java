package com.github.joesta.cruddemo.service;

import com.github.joesta.cruddemo.CrudDemoApplicationTests;
import com.github.joesta.cruddemo.exceptions.CustomerException;
import com.github.joesta.cruddemo.models.Customer;
import com.github.joesta.cruddemo.models.CustomerBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

public class CustomerServiceTest extends CrudDemoApplicationTests {

    @Autowired
    private CustomerService customerService;

    private Customer customer;

    private List<Customer> customers;

    private final Log log = LogFactory.getLog(this.getClass().getName());

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

        Customer expectedCustomer = customerService.updateCustomer(targetCustomer); // return updated customer
        log.info("updated customer object " + expectedCustomer);
        Assertions.assertNotNull(expectedCustomer);
        Assertions.assertNotEquals(expectedCustomer, customer);
    }
}
package com.github.joesta.cruddemo.rest;

import com.github.joesta.cruddemo.exceptions.CustomerException;
import com.github.joesta.cruddemo.models.Customer;
import com.github.joesta.cruddemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @PutMapping("/")
    public void saveCustomer(@RequestBody Customer customer) throws CustomerException {
        customerService.saveCustomer(customer);
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping(path = "/{customernumber}", produces = "application/json")
    public void deleteCustomer(@PathVariable( name = "customernumber") String customerNumber) {
        customerService.deleteCustomer(customerNumber);
    }

    @GetMapping(path = "/{customernumber}", produces = "application/json")
    public void getCustomer(@PathVariable( name = "customernumber") String customerNumber) {
        customerService.findByCustomerNumber(customerNumber);
    }

    @GetMapping(path = "/", produces = "application/json")
    public List<Customer> findAllCustomers() {
        return customerService.findAllCustomers();
    }
}

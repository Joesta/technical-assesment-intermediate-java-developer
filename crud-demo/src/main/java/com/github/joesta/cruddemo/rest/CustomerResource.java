package com.github.joesta.cruddemo.rest;

import com.github.joesta.cruddemo.exceptions.CustomerException;
import com.github.joesta.cruddemo.models.Customer;
import com.github.joesta.cruddemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<Customer> deleteCustomer(@PathVariable( name = "customernumber") String customerNumber) {
        return customerService.deleteCustomer(customerNumber);
    }

    @GetMapping(path = "/{customernumber}", produces = "application/json")
    public Optional<Customer> findByCustomerNumber(@PathVariable( name = "customernumber") String customerNumber) {
        return customerService.findByCustomerNumber(customerNumber);
    }

    @GetMapping(path = "/", produces = "application/json")
    public List<Customer> findAllCustomers() {
        return customerService.findAllCustomers();
    }
}

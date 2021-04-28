package com.github.joesta.cruddemo.rest;

import com.github.joesta.cruddemo.exceptions.CustomerException;
import com.github.joesta.cruddemo.models.Customer;
import com.github.joesta.cruddemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}

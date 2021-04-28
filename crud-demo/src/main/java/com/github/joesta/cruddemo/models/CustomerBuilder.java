package com.github.joesta.cruddemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBuilder {
    private String customerNumber;
    private String firstName;
    private String lastName;
    private boolean status;
    private Contact contact;
    private Address address;

    public static Customer buildACustomer(final int i) {
        String customerNumber = "customerNumber" + i;
        String firstName = "Joesta" + i;
        String lastName = "Sebolela" + i;
        boolean status = true;
        Contact contact = new Contact();
        Address address = new Address();

        return new CustomerBuilder(customerNumber, firstName, lastName, status, contact, address).build();
    }

    public static List<Customer> buildCustomers(final int noOfCustomers) {
        List<Customer> customers = new ArrayList<>();
        for(int i = 0; i < noOfCustomers; i ++) {
            customers.add(buildACustomer(i));
        }

        return customers;
    }

    private Customer build() {
        Customer customer = new Customer();
        customer.setCustomerNumber(customerNumber);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setContact(contact);
        customer.setStatus(status);
        customer.setAddress(address);

        return customer;
    }

}

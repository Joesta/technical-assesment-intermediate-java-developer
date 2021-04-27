package com.github.joesta.cruddemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    private String customerNumber;
    private String firstName;
    private String lastName;
    private boolean status;
    private Contact contact;
    private Address address;
}

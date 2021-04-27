package com.github.joesta.cruddemo.repository;

import com.github.joesta.cruddemo.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}

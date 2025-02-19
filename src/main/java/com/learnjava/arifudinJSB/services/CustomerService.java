package com.learnjava.arifudinJSB.services;

import com.learnjava.arifudinJSB.models.CustomerModel;
import com.learnjava.arifudinJSB.repositorys.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Create or Update Customer
    public CustomerModel saveCustomer(CustomerModel customer) {
        return customerRepository.save(customer);
    }

    // Get all customers
    public List<CustomerModel> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    public Optional<CustomerModel> getCustomerById(UUID id) {
        return customerRepository.findById(id);
    }

    // Delete customer by ID
    public void deleteCustomerById(UUID id) {
        customerRepository.deleteById(id);
    }
}

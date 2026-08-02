package com.vicma.microservices.customer_microservice.customer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vicma.microservices.customer_microservice.exceptions.CustomerNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String saveCustomer(CustomerRequest request) {
        var customer = mapper.toCustomer(request);
        var savedCustomer = repository.save(customer);
        return savedCustomer.getId();
    }

    public CustomerResponse getCustomerById(String customerId) {
        var customer = repository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with ID %s not found", customerId)));
        return mapper.toCustomerResponse(customer);
    }

    public List<CustomerResponse> getAllCustomers() {
        return repository.findAll().stream()
                .map(mapper::toCustomerResponse)
                .toList();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.getId())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with ID %s not found", request.getId())));
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        customer.setCity(request.getCity());
        repository.save(customer);
    }

    public void deleteCustomerById(String customerId) {
        repository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with ID %s not found", customerId)));
        repository.deleteById(customerId);
    }

}

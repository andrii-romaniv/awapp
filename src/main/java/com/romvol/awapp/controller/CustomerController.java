package com.romvol.awapp.controller;

import com.romvol.awapp.domain.Customer;
import com.romvol.awapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/customers/{phoneNumber}")
    public Mono<Customer> getByNumber(@PathVariable() String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }
}

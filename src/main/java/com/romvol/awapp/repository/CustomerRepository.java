package com.romvol.awapp.repository;

import com.romvol.awapp.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

    Mono<Customer> findByPhoneNumber(String phoneNumber);
}

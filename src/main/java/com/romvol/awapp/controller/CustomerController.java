package com.romvol.awapp.controller;

import com.romvol.awapp.domain.Customer;
import com.romvol.awapp.exception.CustomerNotFoundException;
import com.romvol.awapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.validation.Valid;
import static java.util.Comparator.comparing;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/{phoneNumber}")
    public Mono<Customer> findByPhoneNumber(@PathVariable() String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }

    @GetMapping("")
    public Flux<Customer> findAll(@RequestParam(value = "page", defaultValue = "0") long page,
                              @RequestParam(value = "size", defaultValue = "10") long size) {
        return customerRepository.findAll()
                .sort(comparing(Customer::getFirstName).reversed())
                .skip(page * size).take(size);
    }

    @PostMapping("")
    public Mono<Customer> create(@RequestBody @Valid Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    public Mono<Customer> update(@PathVariable("id") String id, @RequestBody @Valid Customer customer) {
        return customerRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomerNotFoundException(id)))
                .map(c -> {
                    c.setFirstName(customer.getFirstName());
                    c.setLastName(customer.getLastName());
                    c.setPhoneNumber(customer.getPhoneNumber());
                    return c;
                })
                .flatMap(customerRepository::save);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> delete(@PathVariable("id") String id) {
        return customerRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomerNotFoundException(id)))
                .flatMap(customerRepository::delete);
    }
}

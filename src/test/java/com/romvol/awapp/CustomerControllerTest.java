package com.romvol.awapp;

import com.romvol.awapp.controller.CustomerController;
import com.romvol.awapp.domain.Customer;
import com.romvol.awapp.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    WebTestClient client;

    @MockBean
    CustomerRepository customerRepository;

    @Test
    public void getAllCustomers() {
        given(customerRepository.findAll())
                .willReturn(Flux.just(Customer.builder()
                        .id("1")
                        .firstName("User")
                        .lastName("Test")
                        .phoneNumber("+380660000000")
                        .build()));

        client.get().uri("/customers").exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].firstName").isEqualTo("User")
                .jsonPath("$[0].lastName").isEqualTo("Test")
                .jsonPath("$[0].id").isEqualTo("1")
                .jsonPath("$[0].phoneNumber").isEqualTo("+380660000000");

        verify(customerRepository, times(1)).findAll();
        verifyNoMoreInteractions(customerRepository);

    }


    @Test
    public void deleteCustomer() {
        Customer customer = Customer.builder()
                .id("1")
                .firstName("User")
                .lastName("Test")
                .phoneNumber("+380660000000")
                .build();

        given(customerRepository.findById("1"))
                .willReturn(Mono.just(customer));
        Mono<Void> mono = Mono.empty();
        given(customerRepository.delete(customer))
                .willReturn(mono);

        client.delete().uri("/customers/1")
                .exchange()
                .expectStatus().isNoContent();

        verify(customerRepository, times(1)).findById(anyString());
        verify(customerRepository, times(1)).delete(any(Customer.class));
        verifyNoMoreInteractions(customerRepository);
    }
}


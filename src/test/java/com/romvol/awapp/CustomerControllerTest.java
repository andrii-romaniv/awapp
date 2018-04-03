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

import java.math.BigDecimal;

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
    public void getCustomerByPhoneNumber() {
        given(customerRepository.findByPhoneNumber("+380660000000"))
                .willReturn(Mono.just(Customer.builder()
                        .id("1")
                        .firstName("User")
                        .lastName("Test")
                        .phoneNumber("+380660000000")
                        .build()));

        client.get().uri("/customers/+380660000000").exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.firstName").isEqualTo("User")
                .jsonPath("$.lastName").isEqualTo("Test")
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.phoneNumber").isEqualTo("+380660000000");

        verify(customerRepository, times(1)).findByPhoneNumber(anyString());
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    public void createCustomer() {
        Customer customer = Customer.builder()
                .firstName("User")
                .lastName("Test")
                .phoneNumber("+380660000000")
                .build();

        given(customerRepository.save(customer))
                .willReturn(Mono.just(Customer.builder()
                        .id("1")
                        .firstName("User")
                        .lastName("Test")
                        .phoneNumber("+380660000000")
                        .build()));

        client.post().uri("/customers").body(BodyInserters.fromObject(customer))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.firstName").isEqualTo("User")
                .jsonPath("$.lastName").isEqualTo("Test")
                .jsonPath("$.phoneNumber").isEqualTo("+380660000000");

        verify(customerRepository, times(1)).save(any(Customer.class));
        verifyNoMoreInteractions(customerRepository);
    }
    
    @Test
    public void updateCustomer() {
        Customer customer = Customer.builder()
                .id("1")
                .firstName("User")
                .lastName("Test")
                .phoneNumber("+380660000000")
                .build();

        given(customerRepository.findById("1"))
                .willReturn(Mono.just(customer));

        customer.setDiscount(BigDecimal.valueOf(10.0));

        given(customerRepository.save(customer))
                .willReturn(Mono.just(
                        Customer.builder()
                                .id("1")
                                .firstName("User")
                                .lastName("Test")
                                .phoneNumber("+380660000000")
                                .discount(BigDecimal.valueOf(10.0))
                                .build()));

        client.put().uri("/customers/1").body(BodyInserters.fromObject(customer))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.firstName").isEqualTo("User")
                .jsonPath("$.lastName").isEqualTo("Test")
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.discount").isEqualTo("10.0")
                .jsonPath("$.phoneNumber").isEqualTo("+380660000000");

        verify(customerRepository, times(1)).findById(anyString());
        verify(customerRepository, times(1)).save(any(Customer.class));
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


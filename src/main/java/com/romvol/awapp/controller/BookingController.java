package com.romvol.awapp.controller;

import com.romvol.awapp.domain.Booking;
import com.romvol.awapp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/booking")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @PostMapping("")
    public Mono<Booking> create(@RequestBody @Valid Booking booking) {
        booking.setCreatedDate(LocalDateTime.now());
        return bookingRepository.save(booking);
    }

    @GetMapping("")
    public Flux<Booking> findAll(@RequestParam(value = "page", defaultValue = "0") long page,
                                 @RequestParam(value = "size", defaultValue = "10") long size) {
        return bookingRepository.findAll()
                .skip(page * size).take(size);
    }
}

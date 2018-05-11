package com.romvol.awapp.controller;

import com.romvol.awapp.domain.Booking;
import com.romvol.awapp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}

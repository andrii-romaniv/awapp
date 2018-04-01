package com.romvol.awapp.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String id) {
        super("Customer with if :" + id +" not found.");
    }
}

package com.example.demo.appdomain.client;

public class NoSuchClientException extends RuntimeException {

    public NoSuchClientException() {
        super("Client not found");
    }
}

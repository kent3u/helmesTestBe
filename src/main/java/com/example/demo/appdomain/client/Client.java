package com.example.demo.appdomain.client;

import lombok.Value;

import java.util.UUID;

@Value(staticConstructor = "of")
public class Client {

    Id id;
    String fullName;

    @Value(staticConstructor = "of")
    public static class Id {
        UUID value;
    }
}

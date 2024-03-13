package com.example.demo.appdomain.client;

import lombok.Value;

@FunctionalInterface
public interface GetClientById {

    Client execute(Request request);

    @Value(staticConstructor = "of")
    class Request {
        Client.Id id;
    }
}

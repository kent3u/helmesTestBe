package com.example.demo.appdomain.involvementapplication;

import com.example.demo.appdomain.client.Client;
import lombok.Value;

import java.util.Optional;

@FunctionalInterface
public interface GetUserInvolvementApplication {

    Optional<InvolvementApplication> execute(Request request);

    @Value(staticConstructor = "of")
    class Request {
        Client.Id id;
    }
}

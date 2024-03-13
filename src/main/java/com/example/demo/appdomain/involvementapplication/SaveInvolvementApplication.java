package com.example.demo.appdomain.involvementapplication;

import lombok.Value;

@FunctionalInterface
public interface SaveInvolvementApplication {

    void execute(Request request);

    @Value(staticConstructor = "of")
    class Request {
        InvolvementApplication involvementApplication;
    }
}

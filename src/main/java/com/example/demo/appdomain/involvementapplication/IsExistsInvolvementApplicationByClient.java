package com.example.demo.appdomain.involvementapplication;

import com.example.demo.appdomain.client.Client;
import lombok.Value;

@FunctionalInterface
public interface IsExistsInvolvementApplicationByClient {

    boolean execute(Request request);

    @Value(staticConstructor = "of")
    class Request {
        Client.Id clientId;
    }
}

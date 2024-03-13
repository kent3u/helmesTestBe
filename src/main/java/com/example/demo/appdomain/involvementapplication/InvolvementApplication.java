package com.example.demo.appdomain.involvementapplication;

import com.example.demo.appdomain.client.Client;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class InvolvementApplication {

    Id id;
    Client.Id clientId;
    boolean termsAgree;


    @Value(staticConstructor = "of")
    public static class Id {
        UUID value;
    }
}

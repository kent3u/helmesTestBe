package com.example.demo.appdomain.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ClientTokenDetails extends User {

    private UUID clientId;

    public ClientTokenDetails(String username, String password, UUID clientId) {
        super(username, password, Set.of());
        this.clientId = clientId;
    }
}

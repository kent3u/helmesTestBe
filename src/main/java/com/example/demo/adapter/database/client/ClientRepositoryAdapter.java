package com.example.demo.adapter.database.client;

import com.example.demo.appdomain.client.Client;
import com.example.demo.appdomain.client.ClientTokenDetails;
import com.example.demo.appdomain.client.GetClientById;
import com.example.demo.appdomain.client.NoSuchClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ClientRepositoryAdapter implements UserDetailsService, GetClientById {

    private final ClientEntityRepository clientEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientEntityRepository.findByUsername(username)
                .map(entity -> new ClientTokenDetails(entity.getUsername(), entity.getPassword(), entity.getId()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    @Override
    public Client execute(Request request) {
        return clientEntityRepository.findById(request.getId().getValue())
                .map(clientEntity -> Client.of(Client.Id.of(clientEntity.getId()), clientEntity.getFullName()))
                .orElseThrow(NoSuchClientException::new);
    }
}

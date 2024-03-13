package com.example.demo.adapter.database.involvementApplication;

import com.example.demo.appdomain.client.Client;
import com.example.demo.appdomain.involvementapplication.GetUserInvolvementApplication;
import com.example.demo.appdomain.involvementapplication.InvolvementApplication;
import com.example.demo.appdomain.involvementapplication.IsExistsInvolvementApplicationByClient;
import com.example.demo.appdomain.involvementapplication.SaveInvolvementApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class InvolvementApplicationRepositoryAdapter implements GetUserInvolvementApplication, IsExistsInvolvementApplicationByClient, SaveInvolvementApplication {

    private final InvolvementApplicationRepository involvementApplicationRepository;

    @Override
    public Optional<InvolvementApplication> execute(GetUserInvolvementApplication.Request request) {
        return involvementApplicationRepository.getByClientId(request.getId().getValue())
                .map(this::toInvolvementApplication);
    }

    @Override
    public boolean execute(IsExistsInvolvementApplicationByClient.Request request) {
        return involvementApplicationRepository.existsByClientId(request.getClientId().getValue());
    }

    @Override
    public void execute(SaveInvolvementApplication.Request request) {
        involvementApplicationRepository.save(toEntity(request.getInvolvementApplication()));
    }

    private InvolvementApplicationEntity toEntity(InvolvementApplication involvementApplication) {
        return InvolvementApplicationEntity.builder()
                .id(involvementApplication.getId().getValue())
                .clientId(involvementApplication.getClientId().getValue())
                .termsAgree(involvementApplication.isTermsAgree())
                .build();
    }

    private InvolvementApplication toInvolvementApplication(InvolvementApplicationEntity entity) {
        return InvolvementApplication.builder()
                .id(InvolvementApplication.Id.of(entity.getId()))
                .clientId(Client.Id.of(entity.getClientId()))
                .termsAgree(entity.isTermsAgree())
                .build();
    }
}

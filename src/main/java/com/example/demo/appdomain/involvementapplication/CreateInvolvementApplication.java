package com.example.demo.appdomain.involvementapplication;

import com.example.demo.appdomain.ValidationErrorCode;
import com.example.demo.appdomain.ValidationException;
import com.example.demo.appdomain.client.Client;
import com.example.demo.appdomain.involvementapplication.validation.ValidateInvolvementApplicationCommon;
import com.example.demo.appdomain.involvementapplication.validation.ValidateInvolvementApplicationCreation;
import com.example.demo.appdomain.involvementapplicationsector.SaveInvolvementApplicationSectors;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateInvolvementApplication {

    private final ValidateInvolvementApplicationCommon validateInvolvementApplicationCommon;
    private final ValidateInvolvementApplicationCreation validateInvolvementApplicationCreation;
    private final SaveInvolvementApplication saveInvolvementApplication;
    private final SaveInvolvementApplicationSectors saveInvolvementApplicationSectors;

    public Response execute(Request request) {
        validateRequest(request);

        UUID newId = UUID.randomUUID();
        InvolvementApplication newInvolvementApplication = toDomainInvolvementApplication(newId, request);

        saveInvolvementApplication.execute(SaveInvolvementApplication.Request.of(newInvolvementApplication));
        saveInvolvementApplicationSectors.execute(SaveInvolvementApplicationSectors.Request.of(newInvolvementApplication.getId(), request.getSelectedSectorIds()));

        return new Response(newInvolvementApplication.getId());
    }

    private InvolvementApplication toDomainInvolvementApplication(UUID newId, Request request) {
        return InvolvementApplication.builder()
                .id(InvolvementApplication.Id.of(newId))
                .clientId(request.getClientId())
                .termsAgree(request.isAgreeTerms())
                .build();
    }

    private void validateRequest(Request request) {
        var errorCodes = new HashSet<ValidationErrorCode>();

        errorCodes.addAll(validateInvolvementApplicationCreation.execute(ValidateInvolvementApplicationCreation.Request.of(request.getClientId())));
        errorCodes.addAll(validateInvolvementApplicationCommon.execute(ValidateInvolvementApplicationCommon.Request.builder()
                .selectedSectorIds(request.getSelectedSectorIds())
                .termsAgree(request.isAgreeTerms())
                .build()));

        if (!errorCodes.isEmpty()) {
            throw ValidationException.of(errorCodes);
        }
    }

    @Value
    @Builder
    public static class Request {
        Client.Id clientId;
        Set<Long> selectedSectorIds;
        boolean agreeTerms;
    }

    @Value(staticConstructor = "of")
    public static class Response {
        InvolvementApplication.Id involvementApplicationId;
    }
}

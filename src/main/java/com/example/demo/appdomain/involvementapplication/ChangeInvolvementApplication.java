package com.example.demo.appdomain.involvementapplication;

import com.example.demo.appdomain.ValidationErrorCode;
import com.example.demo.appdomain.ValidationException;
import com.example.demo.appdomain.client.Client;
import com.example.demo.appdomain.involvementapplication.validation.ValidateInvolvementApplicationChange;
import com.example.demo.appdomain.involvementapplication.validation.ValidateInvolvementApplicationCommon;
import com.example.demo.appdomain.involvementapplicationsector.SaveInvolvementApplicationSectors;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ChangeInvolvementApplication {

    private final ValidateInvolvementApplicationCommon validateInvolvementApplicationCommon;
    private final ValidateInvolvementApplicationChange validateInvolvementApplicationChange;
    private final SaveInvolvementApplicationSectors saveInvolvementApplicationSectors;

    public void execute(Request request) {
        validateRequest(request);

        saveInvolvementApplicationSectors.execute(SaveInvolvementApplicationSectors.Request.of(request.getInvolvementApplicationId(), request.getSelectedSectorIds()));
    }

    private void validateRequest(Request request) {
        var errorCodes = new HashSet<ValidationErrorCode>();

        errorCodes.addAll(validateInvolvementApplicationCommon.execute(ValidateInvolvementApplicationCommon.Request.builder()
                .selectedSectorIds(request.getSelectedSectorIds())
                .termsAgree(request.isTermsAgree())
                .build()));
        errorCodes.addAll(validateInvolvementApplicationChange.execute(ValidateInvolvementApplicationChange.Request.builder()
                .involvementApplicationId(request.getInvolvementApplicationId())
                .clientId(request.getClientId())
                .build()));

        if (!errorCodes.isEmpty()) {
            throw ValidationException.of(errorCodes);
        }
    }

    @Value
    @Builder
    public static class Request {
        InvolvementApplication.Id involvementApplicationId;
        Client.Id clientId;
        Set<Long> selectedSectorIds;
        boolean termsAgree;
    }
}

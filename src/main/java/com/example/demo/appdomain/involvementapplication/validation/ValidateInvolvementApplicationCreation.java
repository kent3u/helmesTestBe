package com.example.demo.appdomain.involvementapplication.validation;

import com.example.demo.appdomain.ValidationErrorCode;
import com.example.demo.appdomain.client.Client;
import com.example.demo.appdomain.involvementapplication.IsExistsInvolvementApplicationByClient;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ValidateInvolvementApplicationCreation {

    private final IsExistsInvolvementApplicationByClient isExistsInvolvementApplicationByClient;

    public Set<ValidationErrorCode> execute(Request request) {
        var errorCodes = new HashSet<ValidationErrorCode>();
        if (isExistsInvolvementApplicationByClient.execute(IsExistsInvolvementApplicationByClient.Request.of(request.getClientId()))) {
            errorCodes.add(InvolvementApplicationCreationErrorCode.INVOLVEMENT_APPLICATION_ALREADY_EXISTS_FOR_CLIENT);
        }
        return errorCodes;
    }

    @Value(staticConstructor = "of")
    public static class Request {
        Client.Id clientId;
    }

    public enum InvolvementApplicationCreationErrorCode implements ValidationErrorCode {
        INVOLVEMENT_APPLICATION_ALREADY_EXISTS_FOR_CLIENT,
    }
}

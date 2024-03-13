package com.example.demo.appdomain.involvementapplication.validation;

import com.example.demo.appdomain.ValidationErrorCode;
import com.example.demo.appdomain.client.Client;
import com.example.demo.appdomain.involvementapplication.GetUserInvolvementApplication;
import com.example.demo.appdomain.involvementapplication.InvolvementApplication;
import com.example.demo.appdomain.involvementapplication.NoInvolvementApplicationForClientException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ValidateInvolvementApplicationChange {

    private final GetUserInvolvementApplication getUserInvolvementApplication;

    public Set<ValidationErrorCode> execute(Request request) {
        var errorCodes = new HashSet<ValidationErrorCode>();

        InvolvementApplication existingInvolvementApplication =
                getUserInvolvementApplication.execute(GetUserInvolvementApplication.Request.of(request.getClientId()))
                        .orElseThrow(NoInvolvementApplicationForClientException::new);

        if (!Objects.equals(request.getInvolvementApplicationId(), existingInvolvementApplication.getId())) {
            errorCodes.add(ChangeInvolvementApplicationValidationErrorCodes.INVOLVEMENT_APPLICATION_EDIT_FORBIDDEN_FOR_ACTOR);
        }

        return errorCodes;
    }

    @Value
    @Builder
    public static class Request {
        InvolvementApplication.Id involvementApplicationId;
        Client.Id clientId;
    }

    public enum ChangeInvolvementApplicationValidationErrorCodes implements ValidationErrorCode {
        INVOLVEMENT_APPLICATION_EDIT_FORBIDDEN_FOR_ACTOR,
    }
}

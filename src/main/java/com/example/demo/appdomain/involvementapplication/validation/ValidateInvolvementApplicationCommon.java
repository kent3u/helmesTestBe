package com.example.demo.appdomain.involvementapplication.validation;

import com.example.demo.appdomain.ValidationErrorCode;
import com.example.demo.appdomain.sector.IsParentSectorSelected;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ValidateInvolvementApplicationCommon {

    private final IsParentSectorSelected isParentSectorSelected;

    public Set<ValidationErrorCode> execute(Request request) {
        var errorCodes = new HashSet<ValidationErrorCode>();

        if (!isParentSectorSelected.execute(IsParentSectorSelected.Request.of(request.getSelectedSectorIds()))) {
            errorCodes.add(CommonInvolvementApplicationValidationErrorCodes.INVOLVEMENT_APPLICATION_SECTOR_PARENT_NOT_SELECTED);
        }

        if (!request.isTermsAgree()) {
            errorCodes.add(CommonInvolvementApplicationValidationErrorCodes.INVOLVEMENT_APPLICATION_TERMS_AGREEMENT_REQUIRED);
        }

        return errorCodes;
    }

    @Value
    @Builder
    public static class Request {
        Set<Long> selectedSectorIds;
        boolean termsAgree;
    }

    public enum CommonInvolvementApplicationValidationErrorCodes implements ValidationErrorCode {
        INVOLVEMENT_APPLICATION_SECTOR_PARENT_NOT_SELECTED,
        INVOLVEMENT_APPLICATION_TERMS_AGREEMENT_REQUIRED,
    }
}

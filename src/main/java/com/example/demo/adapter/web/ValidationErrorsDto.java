package com.example.demo.adapter.web;

import com.example.demo.appdomain.ValidationErrorCode;
import com.example.demo.appdomain.ValidationException;
import lombok.Value;

import java.util.Set;

@Value(staticConstructor = "of")
public class ValidationErrorsDto {
    Set<ValidationErrorCode> errorCodes;

    public static ValidationErrorsDto of(ValidationException value) {
        return ValidationErrorsDto.of(value.getErrors());
    }
}
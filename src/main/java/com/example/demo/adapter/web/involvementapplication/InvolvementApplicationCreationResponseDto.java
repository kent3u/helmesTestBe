package com.example.demo.adapter.web.involvementapplication;

import lombok.Value;

import java.util.UUID;

@Value(staticConstructor = "of")
class InvolvementApplicationCreationResponseDto {
    UUID involvementApplicationId;
}

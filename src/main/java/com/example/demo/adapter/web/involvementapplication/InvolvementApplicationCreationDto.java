package com.example.demo.adapter.web.involvementapplication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class InvolvementApplicationCreationDto {

    private Set<Long> selectedSectorIds;
    private boolean termsAgree;
}

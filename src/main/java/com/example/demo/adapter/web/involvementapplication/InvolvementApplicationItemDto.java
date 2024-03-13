package com.example.demo.adapter.web.involvementapplication;

import com.example.demo.appdomain.involvementapplication.GetInvolvementApplicationItem;
import com.example.demo.appdomain.involvementapplication.InvolvementApplication;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Value
@Builder
class InvolvementApplicationItemDto {

    UUID id;
    String clientFullName;
    Set<Long> selectedSectorIds;
    boolean termsAgree;

    public static InvolvementApplicationItemDto of(GetInvolvementApplicationItem.InvolvementApplicationItem involvementApplicationItem) {
        return InvolvementApplicationItemDto.builder()
                .id(Optional.ofNullable(involvementApplicationItem.getId())
                        .map(InvolvementApplication.Id::getValue)
                        .orElse(null))
                .clientFullName(involvementApplicationItem.getClientFullName())
                .selectedSectorIds(involvementApplicationItem.getSelectedSectorIds())
                .termsAgree(involvementApplicationItem.isTermsAgree())
                .build();
    }
}

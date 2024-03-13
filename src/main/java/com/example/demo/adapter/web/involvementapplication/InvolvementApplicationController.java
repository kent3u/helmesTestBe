package com.example.demo.adapter.web.involvementapplication;

import com.example.demo.appdomain.client.Client;
import com.example.demo.appdomain.involvementapplication.ChangeInvolvementApplication;
import com.example.demo.appdomain.involvementapplication.CreateInvolvementApplication;
import com.example.demo.appdomain.involvementapplication.GetInvolvementApplicationItem;
import com.example.demo.appdomain.involvementapplication.InvolvementApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/involvement-application")
class InvolvementApplicationController {

    private final GetInvolvementApplicationItem getInvolvementApplicationItem;
    private final CreateInvolvementApplication createInvolvementApplication;
    private final ChangeInvolvementApplication changeInvolvementApplication;

    @GetMapping
    public InvolvementApplicationItemDto getUserInvolvementApplication() {
        UUID clientId = UUID.fromString((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        GetInvolvementApplicationItem.InvolvementApplicationItem involvementApplicationItem =
                getInvolvementApplicationItem.execute(GetInvolvementApplicationItem.Request.of(Client.Id.of(clientId)));
        return InvolvementApplicationItemDto.of(involvementApplicationItem);
    }

    @PostMapping
    public InvolvementApplicationCreationResponseDto createInvolvementApplication(@RequestBody InvolvementApplicationCreationDto involvementApplicationCreationDto) {
        UUID clientId = UUID.fromString((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        var response = createInvolvementApplication.execute(CreateInvolvementApplication.Request.builder()
                .clientId(Client.Id.of(clientId))
                .selectedSectorIds(involvementApplicationCreationDto.getSelectedSectorIds())
                .agreeTerms(involvementApplicationCreationDto.isTermsAgree())
                .build());
        return InvolvementApplicationCreationResponseDto.of(response.getInvolvementApplicationId().getValue());
    }

    @PutMapping("/{id}")
    public void changeInvolvementApplication(@PathVariable UUID id,
                                             @RequestBody InvolvementApplicationChangeDto involvementApplicationChangeDto) {
        UUID clientId = UUID.fromString((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        changeInvolvementApplication.execute(ChangeInvolvementApplication.Request.builder()
                .involvementApplicationId(InvolvementApplication.Id.of(id))
                .clientId(Client.Id.of(clientId))
                .selectedSectorIds(involvementApplicationChangeDto.getSelectedSectorIds())
                .termsAgree(involvementApplicationChangeDto.isTermsAgree())
                .build());
    }
}

package com.example.demo.appdomain.involvementapplication;

import com.example.demo.appdomain.client.Client;
import com.example.demo.appdomain.client.GetClientById;
import com.example.demo.appdomain.involvementapplicationsector.FindActiveSectorIdsByInvolvementApplicationId;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class GetInvolvementApplicationItem {

    private final GetUserInvolvementApplication getUserInvolvementApplication;
    private final GetClientById getClientById;
    private final FindActiveSectorIdsByInvolvementApplicationId findInvolvementApplicationSectorIdsByApplicationId;

    public InvolvementApplicationItem execute(Request request) {
        InvolvementApplication involvementApplication = getUserInvolvementApplication.execute(GetUserInvolvementApplication.Request.of(request.getClientId()))
                .orElse(InvolvementApplication.builder().build());
        Client client = getClientById.execute(GetClientById.Request.of(request.clientId));
        Set<Long> sectorIds = findInvolvementApplicationSectorIdsByApplicationId.execute(FindActiveSectorIdsByInvolvementApplicationId.Request.of(involvementApplication.getId()));

        return toInvolvementApplicationItem(involvementApplication, sectorIds, client);
    }

    private InvolvementApplicationItem toInvolvementApplicationItem(InvolvementApplication involvementApplication,
                                                                    Set<Long> selectedSectorIds,
                                                                    Client applicationClient) {
        return InvolvementApplicationItem.builder()
                .id(involvementApplication.getId())
                .clientFullName(applicationClient.getFullName())
                .selectedSectorIds(selectedSectorIds)
                .termsAgree(involvementApplication.isTermsAgree())
                .build();
    }

    @Value(staticConstructor = "of")
    public static class Request {
        Client.Id clientId;
    }

    @Value
    @Builder
    public static class InvolvementApplicationItem {
        InvolvementApplication.Id id;
        String clientFullName;
        Set<Long> selectedSectorIds;
        boolean termsAgree;
    }
}

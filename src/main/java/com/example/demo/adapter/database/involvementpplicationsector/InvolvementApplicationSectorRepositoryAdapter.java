package com.example.demo.adapter.database.involvementpplicationsector;

import com.example.demo.appdomain.involvementapplicationsector.FindActiveSectorIdsByInvolvementApplicationId;
import com.example.demo.appdomain.involvementapplicationsector.SaveInvolvementApplicationSectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

@Component
@RequiredArgsConstructor
class InvolvementApplicationSectorRepositoryAdapter implements FindActiveSectorIdsByInvolvementApplicationId, SaveInvolvementApplicationSectors {

    private final InvolvementApplicationSectorRepository involvementApplicationSectorRepository;

    @Override
    public Set<Long> execute(FindActiveSectorIdsByInvolvementApplicationId.Request request) {
        if (request.getInvolvementApplicationId() == null) {
            return Set.of();
        }
        return involvementApplicationSectorRepository.findByInvolvementApplicationId(request.getInvolvementApplicationId().getValue())
                .stream()
                .map(InvolvementApplicationSectorEntity::getSectorId)
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public void execute(SaveInvolvementApplicationSectors.Request request) {
        Set<InvolvementApplicationSectorEntity> existingEntities = involvementApplicationSectorRepository.findByInvolvementApplicationId(request.getInvolvementApplicationId().getValue());

        saveNewInvolvementApplicationSectors(request, existingEntities);
        deleteOmittedInvolvementApplicationSectors(request.getSelectedSectorIds(), existingEntities);
    }

    private void saveNewInvolvementApplicationSectors(SaveInvolvementApplicationSectors.Request request, Set<InvolvementApplicationSectorEntity> existingEntities) {
        Set<Long> existingSectorIds = existingEntities.stream()
                .map(InvolvementApplicationSectorEntity::getSectorId)
                .collect(Collectors.toUnmodifiableSet());

        Set<InvolvementApplicationSectorEntity> involvementApplicationSectorEntities = request.getSelectedSectorIds().stream()
                .filter(not(existingSectorIds::contains))
                .map(sectorId -> InvolvementApplicationSectorEntity.builder()
                        .sectorId(sectorId)
                        .involvementApplicationId(request.getInvolvementApplicationId().getValue())
                        .build())
                .collect(Collectors.toUnmodifiableSet());
        involvementApplicationSectorRepository.saveAll(involvementApplicationSectorEntities);
    }

    private void deleteOmittedInvolvementApplicationSectors(Set<Long> selectedSectorIds, Set<InvolvementApplicationSectorEntity> existingEntities) {
        Set<InvolvementApplicationSectorEntity> entitiesToDelete = existingEntities.stream()
                .filter(entity -> !selectedSectorIds.contains(entity.getSectorId()))
                .collect(Collectors.toUnmodifiableSet());

        involvementApplicationSectorRepository.deleteAll(entitiesToDelete);
    }
}

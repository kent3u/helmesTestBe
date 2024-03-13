package com.example.demo.appdomain.sector;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class IsParentSectorSelected {

    private final FindAllSectors findAllSectors;

    public boolean execute(Request request) {
        Set<Sector> sectors = findAllSectors.execute();
        Map<Long, Sector> sectorsById = sectors.stream().collect(Collectors.toMap(Sector::getId, Function.identity()));

        return request.getSelectedSectorIds().stream()
                .map(sectorId -> Optional.ofNullable(sectorsById.get(sectorId)).orElseThrow(SectorNotFoundException::new))
                .noneMatch(selectedSector -> selectedSector.getParentId() != null && !request.getSelectedSectorIds().contains(selectedSector.getParentId()));
    }

    @Value(staticConstructor = "of")
    public static class Request {
        Set<Long> selectedSectorIds;
    }
}

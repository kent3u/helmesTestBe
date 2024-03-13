package com.example.demo.adapter.database.sector;

import com.example.demo.appdomain.sector.FindAllSectors;
import com.example.demo.appdomain.sector.Sector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class SectorRepositoryAdapter implements FindAllSectors {

    private final SectorEntityRepository sectorEntityRepository;

    @Override
    public Set<Sector> execute() {
        return sectorEntityRepository.findAll()
                .stream()
                .map(sectorEntity -> Sector.of(sectorEntity.getId(),
                        sectorEntity.getName(),
                        sectorEntity.getParentId()))
                .collect(Collectors.toUnmodifiableSet());
    }
}

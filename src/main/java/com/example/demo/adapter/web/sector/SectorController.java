package com.example.demo.adapter.web.sector;

import com.example.demo.appdomain.sector.FindAllSectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sectors")
class SectorController {

    private final FindAllSectors findAllSectors;

    @GetMapping
    public Set<SectorDto> findAll() {
        return findAllSectors.execute()
                .stream()
                .map(SectorDto::of)
                .collect(Collectors.toSet());
    }
}

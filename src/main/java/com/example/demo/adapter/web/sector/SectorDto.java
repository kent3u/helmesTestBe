package com.example.demo.adapter.web.sector;

import com.example.demo.appdomain.sector.Sector;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
class SectorDto {

    Long id;
    String name;
    Long parentId;

    public static SectorDto of(Sector sector) {
        return SectorDto.builder()
                .id(sector.getId())
                .name(sector.getName())
                .parentId(sector.getParentId())
                .build();
    }
}

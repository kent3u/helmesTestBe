package com.example.demo.adapter.database.involvementpplicationsector;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "involvement_application_sector")
class InvolvementApplicationSectorEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "involvement_application_id")
    private UUID involvementApplicationId;
    @Column(name = "sector_id")
    private Long sectorId;

}

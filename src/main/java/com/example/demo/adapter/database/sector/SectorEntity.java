package com.example.demo.adapter.database.sector;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sector")
class SectorEntity {

    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "parent_id")
    private Long parentId;
}

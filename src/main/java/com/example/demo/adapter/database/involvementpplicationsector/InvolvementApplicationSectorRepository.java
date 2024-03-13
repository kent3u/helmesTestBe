package com.example.demo.adapter.database.involvementpplicationsector;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

interface InvolvementApplicationSectorRepository extends JpaRepository<InvolvementApplicationSectorEntity, Long> {

    Set<InvolvementApplicationSectorEntity> findByInvolvementApplicationId(UUID involvementApplicationId);
}

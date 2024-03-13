package com.example.demo.adapter.database.involvementApplication;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface InvolvementApplicationRepository extends JpaRepository<InvolvementApplicationEntity, UUID> {

    Optional<InvolvementApplicationEntity> getByClientId(UUID clientId);
    boolean existsByClientId(UUID clientId);
}

package com.example.demo.adapter.database.client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface ClientEntityRepository extends JpaRepository<ClientEntity, UUID> {

    Optional<ClientEntity> findByUsername(String username);
}

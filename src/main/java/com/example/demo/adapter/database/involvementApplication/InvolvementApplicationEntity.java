package com.example.demo.adapter.database.involvementApplication;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "involvement_application")
class InvolvementApplicationEntity {

    @Id
    private UUID id;
    @Column(name = "client_id")
    private UUID clientId;
    @Column(name = "terms_agree")
    private boolean termsAgree;
}

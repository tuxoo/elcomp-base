package com.elcompbase.model.entity;

import com.elcompbase.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;
import java.util.UUID;

public record User(
        UUID id,
        String name,
        String loginEmail,
        @JsonIgnore String passwordHash,
        Instant registeredAt,
        Instant visitedAt,
        Role role,
        Boolean isEnabled
) {
}

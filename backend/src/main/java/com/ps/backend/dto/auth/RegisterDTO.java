package com.ps.backend.dto.auth;

import com.ps.backend.model.Role;

public record RegisterDTO(
    String nome,
    String email,
    String senha,
    Role role
) {}

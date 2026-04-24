package com.ps.backend.dto.auth;

public record AuthRequestDTO(
    String email,
    String senha
) {}

package com.ps.backend.exception;

public class UsuarioJaExisteException extends RuntimeException {
    public UsuarioJaExisteException() {
        super("Usuário já existente, por favor faça o Login.");
    }
}

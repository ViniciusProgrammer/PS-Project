package com.ps.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Usuario {

    @GeneratedValue(strategy = GenerationType.UUID)
    private long userId;
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Senha é obrigatório")
    private String senha;

    //Para separar usuarios compradores de administradores
    private String role;


    //Find by email here
    public static Usuario findByEmail(String email) {
        // Implementation for finding user by email
        return null;
    }

}

package com.ps.backend.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "foto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "URL é obrigatória")
    private String url;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser positivo")
    private Double preco;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @ManyToMany(mappedBy = "fotos")
    private List<Carrinho> carrinhos = new ArrayList<>();

    @ManyToMany(mappedBy = "fotosCompradas")
    private List<Usuario> compradores = new ArrayList<>();
}
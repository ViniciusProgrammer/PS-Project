package com.ps.backend.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "evento") 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Evento {

    //tem que ter: id, data, local, descricao, titulo, observacao
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String slug;
    
    @NotBlank(message = "Titulo do evento é obrigatório!")
    private String titulo;

    private String data;
    
    @NotBlank(message = "O local do evento é obrigatório!")
    private String local;   
    
    private String descricao;
    
    private String avisos;
    
    @OneToMany(mappedBy = "evento")
    private List<Foto> fotos;
}

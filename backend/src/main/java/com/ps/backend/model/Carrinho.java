package com.ps.backend.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "carrinho") 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Carrinho  {

    //tem que ter: id, lista de fotos, valor final da compra, desconto aplicado caso haja
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
        name = "carrinho_foto",
        joinColumns = @JoinColumn(name = "carrinho_id"),
        inverseJoinColumns = @JoinColumn(name = "foto_id")
    )
    private List<Foto> fotos;

    private Double valorFinal;
    
    private Double desconto;

}

package com.ps.backend.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(
        name = "carrinho_itens",
        uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "foto_id"})
)
public class CarrinhoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foto_id", nullable = false)
    private Foto foto;

    @Column(name = "adicionado_em", nullable = false, updatable = false)
    private LocalDateTime adicionadoEm;

    @PrePersist
    public void prePersist() {
        if (adicionadoEm == null) {
            adicionadoEm = LocalDateTime.now();
        }
    }

    public CarrinhoItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public LocalDateTime getAdicionadoEm() {
        return adicionadoEm;
    }

    public void setAdicionadoEm(LocalDateTime adicionadoEm) {
        this.adicionadoEm = adicionadoEm;
    }
}

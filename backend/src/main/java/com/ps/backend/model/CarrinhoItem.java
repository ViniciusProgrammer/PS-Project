package com.ps.backend.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "carrinho_itens",
        uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "foto_id"})
)

@Getter
@Setter
@NoArgsConstructor
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
}

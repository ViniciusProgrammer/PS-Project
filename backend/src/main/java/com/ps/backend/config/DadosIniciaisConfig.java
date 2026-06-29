package com.ps.backend.config;

import com.ps.backend.model.Categoria;
import com.ps.backend.model.Evento;
import com.ps.backend.model.Foto;
import com.ps.backend.model.Role;
import com.ps.backend.model.Usuario;
import com.ps.backend.repository.CategoriaRepository;
import com.ps.backend.repository.EventoRepository;
import com.ps.backend.repository.FotoRepository;
import com.ps.backend.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DadosIniciaisConfig {

    @Bean
    CommandLineRunner dadosIniciais(
            CategoriaRepository categoriaRepository,
            EventoRepository eventoRepository,
            FotoRepository fotoRepository,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            criarUsuarioDemo(usuarioRepository, passwordEncoder);

            if (categoriaRepository.count() == 0) {
                categoriaRepository.saveAll(List.of(
                        categoria("Corrida", "corrida"),
                        categoria("Futebol", "futebol"),
                        categoria("Natacao", "natacao")
                ));
            }

            if (eventoRepository.count() > 0) {
                return;
            }

            Categoria corrida = categoriaRepository.findBySlug("corrida").orElseThrow();
            Categoria futebol = categoriaRepository.findBySlug("futebol").orElseThrow();
            Categoria natacao = categoriaRepository.findBySlug("natacao").orElseThrow();

            Evento corridaNatal = evento("Corrida de Rua Natal 2026", "Fotos oficiais da prova de rua em Natal.",
                    "Arena das Dunas", "Natal", "Equipe Athletica", corrida, 35);
            Evento copaPraia = evento("Copa Praia de Futebol", "Lances decisivos e retratos dos atletas.",
                    "Ponta Negra", "Natal", "Marina Costa", futebol, 50);
            Evento meetingNatacao = evento("Meeting de Natacao", "Cobertura completa das baterias e premiacao.",
                    "Sesc Mossoro", "Mossoro", "Rafael Lima", natacao, 70);

            eventoRepository.saveAll(List.of(corridaNatal, copaPraia, meetingNatacao));

            fotoRepository.saveAll(List.of(
                    foto("Sprint final #01", "/images/running.jpg", "Corredor cruzando a linha de chegada.", corridaNatal, "19.90"),
                    foto("Ritmo urbano #02", "/images/running2.jpg", "Pelotao principal na avenida.", corridaNatal, "17.90"),
                    foto("Vitoria no asfalto #03", "/images/running3.jpg", "Celebracao apos a prova.", corridaNatal, "24.90"),
                    foto("Gol da final #11", "/images/soccer ball.jpg", "Chute decisivo na pequena area.", copaPraia, "22.90"),
                    foto("Campo iluminado #12", "/images/soccer field.jpg", "Visao aberta do jogo.", copaPraia, "18.90"),
                    foto("Braçada perfeita #21", "/images/swimming.jpg", "Atleta em prova de velocidade.", meetingNatacao, "21.90"),
                    foto("Quadra aberta #31", "/images/tennis.jpg", "Disputa em quadra rapida.", meetingNatacao, "16.90"),
                    foto("Pelotao de elite #41", "/images/cycling.jpg", "Ciclistas em subida tecnica.", corridaNatal, "25.90")
            ));
        };
    }

    private void criarUsuarioDemo(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        usuarioRepository.findByEmail("demo@athletica.com").orElseGet(() -> {
            Usuario usuario = new Usuario();
            usuario.setNome("Cliente Demo");
            usuario.setEmail("demo@athletica.com");
            usuario.setSenha(passwordEncoder.encode("123456"));
            usuario.setRole(Role.USER);
            return usuarioRepository.save(usuario);
        });
    }

    private Categoria categoria(String nome, String slug) {
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        categoria.setSlug(slug);
        return categoria;
    }

    private Evento evento(String titulo, String descricao, String local, String cidade,
                          String fotografo, Categoria categoria, int diasAFrente) {
        Evento evento = new Evento();
        evento.setTitulo(titulo);
        evento.setDescricao(descricao);
        evento.setLocal(local);
        evento.setCidade(cidade);
        evento.setPais("Brasil");
        evento.setFotografo(fotografo);
        evento.setCategoria(categoria);
        evento.setDataEvento(LocalDateTime.now().plusDays(diasAFrente));
        return evento;
    }

    private Foto foto(String nome, String url, String descricao, Evento evento, String preco) {
        Foto foto = new Foto();
        foto.setNome(nome);
        foto.setUrl(url);
        foto.setDescricao(descricao);
        foto.setEvento(evento);
        foto.setPreco(new BigDecimal(preco));
        return foto;
    }
}

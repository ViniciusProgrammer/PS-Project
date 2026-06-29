package com.ps.backend.controller.pages;

import com.ps.backend.model.Usuario;
import com.ps.backend.repository.CategoriaRepository;
import com.ps.backend.service.EventoService;
import com.ps.backend.service.FotoService;
import com.ps.backend.service.PedidoService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaginasController {

    private final EventoService eventoService;
    private final FotoService fotoService;
    private final CategoriaRepository categoriaRepository;
    private final PedidoService pedidoService;

    public PaginasController(
            EventoService eventoService,
            FotoService fotoService,
            CategoriaRepository categoriaRepository,
            PedidoService pedidoService) {
        this.eventoService = eventoService;
        this.fotoService = fotoService;
        this.categoriaRepository = categoriaRepository;
        this.pedidoService = pedidoService;
    }

    @GetMapping("/")
    public String raiz() {
        return "redirect:/Home";
    }

    @GetMapping({"/login", "/auth/login"})
    public String login() {
        return "login";
    }

    @GetMapping("/Home")
    public String home(@AuthenticationPrincipal Usuario usuario, Model model) {
        model.addAttribute("usuario", usuario);
        model.addAttribute("eventos", eventoService.listar(null, null).stream().limit(3).toList());
        model.addAttribute("fotos", fotoService.listar().stream().limit(6).toList());
        return "home";
    }

    @GetMapping("/eventos")
    public String eventos(
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) String busca,
            @AuthenticationPrincipal Usuario usuario,
            Model model) {
        model.addAttribute("usuario", usuario);
        model.addAttribute("eventos", eventoService.listar(categoriaId, busca));
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("busca", busca);
        model.addAttribute("categoriaId", categoriaId);
        return "eventos";
    }

    @GetMapping("/fotos")
    public String fotos(@RequestParam(required = false) Long eventoId, @AuthenticationPrincipal Usuario usuario, Model model) {
        var eventos = eventoService.listar(null, null);
        var fotos = eventoId == null ? fotoService.listar() : fotoService.listarPorEvento(eventoId);
        var eventoSelecionado = eventoId == null
                ? null
                : eventos.stream().filter(evento -> evento.getId().equals(eventoId)).findFirst().orElse(null);

        model.addAttribute("usuario", usuario);
        model.addAttribute("eventos", eventos);
        model.addAttribute("fotos", fotos);
        model.addAttribute("eventoSelecionado", eventoSelecionado);
        model.addAttribute("eventoId", eventoId);
        return "fotos";
    }

    @GetMapping("/carrinho")
    public String carrinho(@AuthenticationPrincipal Usuario usuario, Model model) {
        if (usuario == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", usuario);
        return "carrinho";
    }

    @GetMapping("/minhas-fotos")
    public String minhasFotos(@AuthenticationPrincipal Usuario usuario, Model model) {
        if (usuario == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("pedidos", pedidoService.listarPorUsuario(usuario.getId()));
        return "minhas_fotos";
    }
}

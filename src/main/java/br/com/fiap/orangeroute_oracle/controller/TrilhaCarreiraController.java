package br.com.fiap.orangeroute_oracle.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.orangeroute_oracle.entity.TrilhaCarreira;
import br.com.fiap.orangeroute_oracle.service.TrilhaCarreiraService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/trilhas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // ✅ libera acesso a todas as origens (para testes)
public class TrilhaCarreiraController {

    private final TrilhaCarreiraService trilhaService;

    @GetMapping
    public List<TrilhaCarreira> listar() {
        return trilhaService.listarTodos();
    }

    @GetMapping("/{id}")
    public TrilhaCarreira buscar(@PathVariable Long id) {
        return trilhaService.buscarPorId(id);
    }
}

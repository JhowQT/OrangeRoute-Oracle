package br.com.fiap.orangeroute_oracle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.orangeroute_oracle.dto.FavoritoCreateDTO;
import br.com.fiap.orangeroute_oracle.dto.FavoritoResponseDTO;
import br.com.fiap.orangeroute_oracle.service.FavoritoService;

@RestController
@RequestMapping("/favoritos")
@CrossOrigin(origins = "*") // Permite chamadas do front (ex: localhost:5500)
public class FavoritoController {

    @Autowired
    private FavoritoService service;

    @GetMapping
    public List<FavoritoResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<FavoritoResponseDTO> listarPorUsuario(@PathVariable Long idUsuario) {
        return service.listarPorUsuario(idUsuario);
    }

    @PostMapping
    public FavoritoResponseDTO criar(@RequestBody FavoritoCreateDTO dto) {
        return service.criar(dto);
    }

    @DeleteMapping("/{idFavorito}")
    public void remover(@PathVariable Long idFavorito) {
        service.remover(idFavorito);
    }
}

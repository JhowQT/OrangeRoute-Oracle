package br.com.fiap.orangeroute_oracle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.orangeroute_oracle.dto.ComentarioCreateDTO;
import br.com.fiap.orangeroute_oracle.dto.ComentarioResponseDTO;
import br.com.fiap.orangeroute_oracle.service.ComentarioService;

@RestController
@RequestMapping("/comentarios")
@CrossOrigin(origins = "*") // Permite chamadas do front (ex: localhost:5500)
public class ComentarioController {

    @Autowired
    private ComentarioService service;

    @GetMapping
    public List<ComentarioResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/trilha/{idTrilha}")
    public List<ComentarioResponseDTO> listarPorTrilha(@PathVariable Long idTrilha) {
        return service.listarPorTrilha(idTrilha);
    }

    @PostMapping
    public ComentarioResponseDTO criar(@RequestBody ComentarioCreateDTO dto) {
        return service.criar(dto);
    }
}

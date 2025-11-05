package br.com.fiap.orangeroute_oracle.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.orangeroute_oracle.entity.TagCarreira;
import br.com.fiap.orangeroute_oracle.repository.TagCarreiraRepository;

@RestController
@RequestMapping("/tags")
@CrossOrigin(origins = "*")
public class TagCarreiraController {

    @Autowired
    private TagCarreiraRepository repository;

    // 🔹 Listar todas as tags de carreira
    @GetMapping
    public List<TagCarreira> listarTodas() {
        return repository.findAll();
    }

    // 🔹 Buscar uma tag específica por ID
    @GetMapping("/{id}")
    public Optional<TagCarreira> buscarPorId(@PathVariable Long id) {
        return repository.findById(id);
    }

    // 🔹 Listar tags associadas a uma trilha específica
    @GetMapping("/trilha/{idTrilha}")
    public List<TagCarreira> listarPorTrilha(@PathVariable Long idTrilha) {
        return repository.findByTrilhaCarreiraIdTrilhaCarreira(idTrilha);
    }
}

package br.com.fiap.orangeroute_oracle.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.orangeroute_oracle.entity.Link;
import br.com.fiap.orangeroute_oracle.repository.LinkRepository;

@RestController
@RequestMapping("/links")
@CrossOrigin(origins = "*") // Permite chamadas do front (ex: localhost:5500)
public class LinkController {

    @Autowired
    private LinkRepository repository;

    // 🔹 Listar todos os links cadastrados
    @GetMapping
    public List<Link> listarTodos() {
        return repository.findAll();
    }

    // 🔹 Buscar um link específico pelo ID
    @GetMapping("/{id}")
    public Optional<Link> buscarPorId(@PathVariable Long id) {
        return repository.findById(id);
    }

    // 🔹 Listar todos os links de uma trilha específica
    @GetMapping("/trilha/{idTrilha}")
    public List<Link> listarPorTrilha(@PathVariable Long idTrilha) {
        return repository.findByTrilhaCarreiraIdTrilhaCarreira(idTrilha);
    }
}

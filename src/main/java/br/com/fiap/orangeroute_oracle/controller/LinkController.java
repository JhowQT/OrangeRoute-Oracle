package br.com.fiap.orangeroute_oracle.controller;

import br.com.fiap.orangeroute_oracle.entity.Link;
import br.com.fiap.orangeroute_oracle.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/links")
@CrossOrigin(origins = "*") // Permite chamadas do front (ex: localhost:5500)
public class LinkController {

    @Autowired
    private LinkRepository repository;
    @GetMapping
    public ResponseEntity<Map<String, Object>> listarTodos() {
        List<Link> links = repository.findAll();

        List<Map<String, Object>> conteudo = new ArrayList<>();
        for (Link l : links) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("idLink", l.getIdLink());
            item.put("tituloLink", l.getTituloLink());
            item.put("conteudoLink", l.getConteudoLink());
            item.put("idTrilhaCarreira", l.getTrilhaCarreira().getIdTrilhaCarreira());
            item.put("_links", Map.of(
                    "self", "/links/" + l.getIdLink(),
                    "porTrilha", "/links/trilha/" + l.getTrilhaCarreira().getIdTrilhaCarreira(),
                    "all", "/links"
            ));
            conteudo.add(item);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("count", conteudo.size());
        response.put("data", conteudo);
        response.put("_links", Map.of("self", "/links"));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorId(@PathVariable Long id) {
        Optional<Link> link = repository.findById(id);

        if (link.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Link l = link.get();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("idLink", l.getIdLink());
        body.put("tituloLink", l.getTituloLink());
        body.put("conteudoLink", l.getConteudoLink());
        body.put("idTrilhaCarreira", l.getTrilhaCarreira().getIdTrilhaCarreira());
        body.put("_links", Map.of(
                "self", "/links/" + id,
                "all", "/links",
                "porTrilha", "/links/trilha/" + l.getTrilhaCarreira().getIdTrilhaCarreira()
        ));

        return ResponseEntity.ok(body);
    }

    @GetMapping("/trilha/{idTrilha}")
    public ResponseEntity<Map<String, Object>> listarPorTrilha(@PathVariable Long idTrilha) {
        List<Link> links = repository.findByTrilhaCarreiraIdTrilhaCarreira(idTrilha);

        List<Map<String, Object>> conteudo = new ArrayList<>();
        for (Link l : links) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("idLink", l.getIdLink());
            item.put("tituloLink", l.getTituloLink());
            item.put("conteudoLink", l.getConteudoLink());
            item.put("_links", Map.of(
                    "self", "/links/" + l.getIdLink(),
                    "all", "/links",
                    "trilha", "/trilhas/" + idTrilha
            ));
            conteudo.add(item);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("count", conteudo.size());
        response.put("data", conteudo);
        response.put("_links", Map.of(
                "self", "/links/trilha/" + idTrilha,
                "trilha", "/trilhas/" + idTrilha,
                "all", "/links"
        ));

        return ResponseEntity.ok(response);
    }
}

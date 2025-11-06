package br.com.fiap.orangeroute_oracle.controller;

import br.com.fiap.orangeroute_oracle.entity.TrilhaCarreira;
import br.com.fiap.orangeroute_oracle.service.TrilhaCarreiraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/trilhas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TrilhaCarreiraController {

    private final TrilhaCarreiraService trilhaService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listar() {
        List<TrilhaCarreira> trilhas = trilhaService.listarTodos();

        List<Map<String, Object>> lista = new ArrayList<>();
        for (TrilhaCarreira t : trilhas) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("idTrilhaCarreira", t.getIdTrilhaCarreira());
            item.put("tituloTrilha", t.getTituloTrilha());
            item.put("conteudoTrilha", t.getConteudoTrilha());

            item.put("_links", Map.of(
                    "self", "/trilhas/" + t.getIdTrilhaCarreira(),
                    "all", "/trilhas"
            ));

            lista.add(item);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("count", lista.size());
        response.put("data", lista);
        response.put("_links", Map.of(
                "self", "/trilhas"
        ));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscar(@PathVariable Long id) {
        TrilhaCarreira trilha = trilhaService.buscarPorId(id);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("idTrilhaCarreira", trilha.getIdTrilhaCarreira());
        body.put("tituloTrilha", trilha.getTituloTrilha());
        body.put("conteudoTrilha", trilha.getConteudoTrilha());
        body.put("_links", Map.of(
                "self", "/trilhas/" + id,
                "all", "/trilhas"
        ));

        return ResponseEntity.ok(body);
    }
}

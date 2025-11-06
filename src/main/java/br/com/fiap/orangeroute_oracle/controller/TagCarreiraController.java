package br.com.fiap.orangeroute_oracle.controller;

import br.com.fiap.orangeroute_oracle.entity.TagCarreira;
import br.com.fiap.orangeroute_oracle.repository.TagCarreiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/tags")
@CrossOrigin(origins = "*")
public class TagCarreiraController {

    @Autowired
    private TagCarreiraRepository repository;

    // ==========================================================
    // LISTAR TODAS AS TAGS
    // ==========================================================
    @GetMapping
    public ResponseEntity<Map<String, Object>> listarTodas() {
        List<TagCarreira> lista = repository.findAll();

        List<Map<String, Object>> conteudo = new ArrayList<>();
        for (TagCarreira tc : lista) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("idTagCarreira", tc.getIdTagCarreira());
            item.put("idTrilhaCarreira", tc.getTrilhaCarreira().getIdTrilhaCarreira());
            item.put("idTag", tc.getTag().getIdTag());
            item.put("nomeTag", tc.getTag().getNomeTag()); // ✅ AGORA RETORNA O NOME DA TAG

            item.put("_links", Map.of(
                    "self", "/tags/" + tc.getIdTagCarreira(),
                    "porTrilha", "/tags/trilha/" + tc.getTrilhaCarreira().getIdTrilhaCarreira(),
                    "all", "/tags"
            ));
            conteudo.add(item);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("count", conteudo.size());
        response.put("data", conteudo);
        response.put("_links", Map.of("self", "/tags"));

        return ResponseEntity.ok(response);
    }

    // ==========================================================
    // BUSCAR TAG POR ID
    // ==========================================================
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorId(@PathVariable Long id) {
        Optional<TagCarreira> resultado = repository.findById(id);

        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TagCarreira tc = resultado.get();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("idTagCarreira", tc.getIdTagCarreira());
        body.put("idTrilhaCarreira", tc.getTrilhaCarreira().getIdTrilhaCarreira());
        body.put("idTag", tc.getTag().getIdTag());
        body.put("nomeTag", tc.getTag().getNomeTag()); // ✅ NOVO CAMPO
        body.put("_links", Map.of(
                "self", "/tags/" + id,
                "all", "/tags",
                "porTrilha", "/tags/trilha/" + tc.getTrilhaCarreira().getIdTrilhaCarreira()
        ));

        return ResponseEntity.ok(body);
    }

    // ==========================================================
    // LISTAR TAGS POR TRILHA
    // ==========================================================
    @GetMapping("/trilha/{idTrilha}")
    public ResponseEntity<Map<String, Object>> listarPorTrilha(@PathVariable Long idTrilha) {
        List<TagCarreira> lista = repository.findByTrilhaCarreiraIdTrilhaCarreira(idTrilha);

        List<Map<String, Object>> conteudo = new ArrayList<>();
        for (TagCarreira tc : lista) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("idTagCarreira", tc.getIdTagCarreira());
            item.put("idTag", tc.getTag().getIdTag());
            item.put("nomeTag", tc.getTag().getNomeTag()); // ✅ ADICIONADO
            item.put("idTrilhaCarreira", idTrilha);

            item.put("_links", Map.of(
                    "self", "/tags/" + tc.getIdTagCarreira(),
                    "trilha", "/trilhas/" + idTrilha,
                    "all", "/tags"
            ));

            conteudo.add(item);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("count", conteudo.size());
        response.put("data", conteudo);
        response.put("_links", Map.of(
                "self", "/tags/trilha/" + idTrilha,
                "trilha", "/trilhas/" + idTrilha,
                "all", "/tags"
        ));

        return ResponseEntity.ok(response);
    }
}

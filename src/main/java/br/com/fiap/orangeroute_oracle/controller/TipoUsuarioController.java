package br.com.fiap.orangeroute_oracle.controller;

import br.com.fiap.orangeroute_oracle.entity.TipoUsuario;
import br.com.fiap.orangeroute_oracle.service.TipoUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/tipo-usuario")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") 
public class TipoUsuarioController {

    private final TipoUsuarioService tipoUsuarioService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarTodos() {
        List<TipoUsuario> tipos = tipoUsuarioService.listarTodos();

        List<Map<String, Object>> conteudo = new ArrayList<>();
        for (TipoUsuario tipo : tipos) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("idTipoUsuario", tipo.getIdTipoUsuario());
            item.put("nomeTipoUsuario", tipo.getNomeTipoUsuario()); // ✅ nome correto

            Map<String, String> links = new LinkedHashMap<>();
            links.put("self", "/tipo-usuario/" + tipo.getIdTipoUsuario());
            links.put("all", "/tipo-usuario");
            item.put("_links", links);

            conteudo.add(item);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("count", conteudo.size());
        response.put("data", conteudo);
        response.put("_links", Map.of(
                "self", "/tipo-usuario",
                "create", "/tipo-usuario (POST)"
        ));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorId(@PathVariable Long id) {
        TipoUsuario tipo = tipoUsuarioService.buscarPorId(id);

        if (tipo == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("idTipoUsuario", tipo.getIdTipoUsuario());
        body.put("nomeTipoUsuario", tipo.getNomeTipoUsuario()); // ✅ nome correto
        body.put("_links", Map.of(
                "self", "/tipo-usuario/" + id,
                "all", "/tipo-usuario",
                "update", "/tipo-usuario/" + id + " (PUT)",
                "delete", "/tipo-usuario/" + id + " (DELETE)"
        ));

        return ResponseEntity.ok(body);
    }
}

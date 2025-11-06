package br.com.fiap.orangeroute_oracle.controller;

import br.com.fiap.orangeroute_oracle.dto.UsuarioCreateDTO;
import br.com.fiap.orangeroute_oracle.dto.UsuarioResponseDTO;
import br.com.fiap.orangeroute_oracle.dto.UsuarioUpdateDTO;
import br.com.fiap.orangeroute_oracle.dto.UsuarioFotoDTO;
import br.com.fiap.orangeroute_oracle.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> cadastrarUsuario(@Valid @RequestBody UsuarioCreateDTO dto) {
        UsuarioResponseDTO novoUsuario = usuarioService.cadastrarUsuario(dto);
        URI location = URI.create("/usuario/" + novoUsuario.getIdUsuario());

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("usuario", novoUsuario);
        body.put("_links", Map.of(
                "self", "/usuario/" + novoUsuario.getIdUsuario(),
                "all", "/usuario",
                "update", "/usuario/" + novoUsuario.getIdUsuario() + " (PUT)",
                "delete", "/usuario/" + novoUsuario.getIdUsuario() + " (DELETE)"
        ));

        return ResponseEntity.created(location).body(body);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listarUsuarios();

        List<Map<String, Object>> lista = new ArrayList<>();
        for (UsuarioResponseDTO u : usuarios) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("idUsuario", u.getIdUsuario());
            item.put("nomeUsuario", u.getNomeUsuario());
            item.put("email", u.getEmail());
            item.put("tipoUsuario", u.getNomeTipoUsuario());
            item.put("ativo", u.getAtivo());
            item.put("_links", Map.of(
                    "self", "/usuario/" + u.getIdUsuario(),
                    "update", "/usuario/" + u.getIdUsuario() + " (PUT)",
                    "delete", "/usuario/" + u.getIdUsuario() + " (DELETE)"
            ));
            lista.add(item);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("count", lista.size());
        response.put("data", lista);
        response.put("_links", Map.of(
                "self", "/usuario",
                "create", "/usuario (POST)"
        ));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarUsuarioPorId(@PathVariable Long id) {
        UsuarioResponseDTO usuario = usuarioService.buscarUsuarioPorId(id);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("usuario", usuario);
        body.put("_links", Map.of(
                "self", "/usuario/" + id,
                "all", "/usuario",
                "update", "/usuario/" + id + " (PUT)",
                "delete", "/usuario/" + id + " (DELETE)"
        ));

        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioUpdateDTO dto
    ) {
        UsuarioResponseDTO atualizado = usuarioService.atualizarUsuario(id, dto);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("usuario", atualizado);
        body.put("_links", Map.of(
                "self", "/usuario/" + id,
                "all", "/usuario",
                "update", "/usuario/" + id,
                "delete", "/usuario/" + id + " (DELETE)"
        ));

        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}/foto")
    public ResponseEntity<Map<String, Object>> atualizarFoto(
            @PathVariable Long id,
            @ModelAttribute UsuarioFotoDTO dto
    ) {
        UsuarioResponseDTO atualizado = usuarioService.atualizarFoto(id, dto);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("usuario", atualizado);
        body.put("_links", Map.of(
                "self", "/usuario/" + id + "/foto",
                "usuario", "/usuario/" + id,
                "all", "/usuario"
        ));

        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Usuário excluído com sucesso.");
        response.put("_links", Map.of(
                "all", "/usuario",
                "create", "/usuario (POST)"
        ));

        return ResponseEntity.ok(response);
    }
}

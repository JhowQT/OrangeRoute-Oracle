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
import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permite chamadas do front (ex: localhost:5500)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@Valid @RequestBody UsuarioCreateDTO dto) {
        UsuarioResponseDTO novoUsuario = usuarioService.cadastrarUsuario(dto);
        URI location = URI.create("/usuario/" + novoUsuario.getIdUsuario());
        return ResponseEntity.created(location).body(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Long id) {
        UsuarioResponseDTO usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioUpdateDTO dto
    ) {
        UsuarioResponseDTO usuarioAtualizado = usuarioService.atualizarUsuario(id, dto);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @PutMapping("/{id}/foto")
    public ResponseEntity<UsuarioResponseDTO> atualizarFoto(
            @PathVariable Long id,
            @ModelAttribute UsuarioFotoDTO dto
    ) {
        UsuarioResponseDTO usuarioAtualizado = usuarioService.atualizarFoto(id, dto);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}

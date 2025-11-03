package br.com.fiap.orangeroute_oracle.controller;

import br.com.fiap.orangeroute_oracle.entity.TipoUsuario;
import br.com.fiap.orangeroute_oracle.service.TipoUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-usuario")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // permite chamadas do front-end (localhost:5500)
public class TipoUsuarioController {

    private final TipoUsuarioService tipoUsuarioService;

    @GetMapping
    public ResponseEntity<List<TipoUsuario>> listarTodos() {
        List<TipoUsuario> tipos = tipoUsuarioService.listarTodos();
        return ResponseEntity.ok(tipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> buscarPorId(@PathVariable Long id) {
        TipoUsuario tipo = tipoUsuarioService.buscarPorId(id);
        return ResponseEntity.ok(tipo);
    }
}

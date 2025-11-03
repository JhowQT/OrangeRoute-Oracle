package br.com.fiap.orangeroute_oracle.controller;

import br.com.fiap.orangeroute_oracle.dto.LoginDTO;
import br.com.fiap.orangeroute_oracle.dto.LoginResponseDTO;
import br.com.fiap.orangeroute_oracle.entity.Usuario;
import br.com.fiap.orangeroute_oracle.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // para permitir requisições do front
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findAll().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(loginDTO.getEmail()) &&
                             u.getSenha().equals(loginDTO.getSenha()))
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            return ResponseEntity.status(401).body("Email ou senha inválidos");
        }

        String token = "fake-jwt-token-" + usuario.getIdUsuario();

        return ResponseEntity.ok(new LoginResponseDTO(token, usuario));
    }
}

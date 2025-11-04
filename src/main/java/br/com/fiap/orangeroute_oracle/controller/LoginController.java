package br.com.fiap.orangeroute_oracle.controller;

import br.com.fiap.orangeroute_oracle.dto.LoginDTO;
import br.com.fiap.orangeroute_oracle.dto.LoginResponseDTO;
import br.com.fiap.orangeroute_oracle.entity.Usuario;
import br.com.fiap.orangeroute_oracle.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            Usuario usuario = usuarioRepository.findAll().stream()
                    .filter(u -> u.getEmail().equalsIgnoreCase(loginDTO.getEmail()) &&
                                 u.getSenha().trim().equals(loginDTO.getSenha().trim()))
                    .findFirst()
                    .orElse(null);

            if (usuario == null) {
                return ResponseEntity.status(401)
                        .body(Map.of("message", "E-mail ou senha incorretos."));
            }

            String token = "fake-jwt-token-" + usuario.getIdUsuario();

            return ResponseEntity.ok(new LoginResponseDTO(token, usuario));

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("message", "Erro interno ao processar login."));
        }
    }
}

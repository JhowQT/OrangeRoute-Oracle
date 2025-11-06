package br.com.fiap.orangeroute_oracle.controller;

import br.com.fiap.orangeroute_oracle.dto.LoginDTO;
import br.com.fiap.orangeroute_oracle.dto.LoginResponseDTO;
import br.com.fiap.orangeroute_oracle.entity.Usuario;
import br.com.fiap.orangeroute_oracle.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
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

                Map<String, Object> erro = new LinkedHashMap<>();
                erro.put("message", "E-mail ou senha incorretos.");
                erro.put("_links", Map.of(
                        "self", "/auth/login",
                        "cadastro", "/usuario (POST)"
                ));
                return ResponseEntity.status(401).body(erro);
            }

            String token = "fake-jwt-token-" + usuario.getIdUsuario();

            LoginResponseDTO dto = new LoginResponseDTO(token, usuario);


            Map<String, Object> body = new LinkedHashMap<>();
            body.put("data", dto);
            body.put("_links", Map.of(
                    "self", "/auth/login",
                    "perfil", "/usuario/" + usuario.getIdUsuario(),
                    "trilhas", "/trilhas",
                    "favoritos", "/favoritos",
                    "comentarios", "/comentarios"
            ));

            return ResponseEntity.ok(body);

        } catch (Exception e) {
            Map<String, Object> erro = new LinkedHashMap<>();
            erro.put("message", "Erro interno ao processar login.");
            erro.put("_links", Map.of("self", "/auth/login"));
            return ResponseEntity.status(500).body(erro);
        }
    }
}

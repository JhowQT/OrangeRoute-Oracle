package br.com.fiap.orangeroute_oracle.dto;

import br.com.fiap.orangeroute_oracle.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private Usuario usuario;
}

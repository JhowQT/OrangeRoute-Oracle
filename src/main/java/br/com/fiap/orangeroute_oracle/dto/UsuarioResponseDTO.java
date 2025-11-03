package br.com.fiap.orangeroute_oracle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Long idUsuario;
    private String nomeUsuario;
    private String email;

    private Long idTipoUsuario;
    private String nomeTipoUsuario;
    
    private String ativo;
    private String fotoBase64;
}

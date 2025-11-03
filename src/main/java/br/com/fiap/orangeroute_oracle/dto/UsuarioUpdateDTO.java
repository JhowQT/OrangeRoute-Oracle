package br.com.fiap.orangeroute_oracle.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioUpdateDTO {
    private String nomeUsuario;
    private String email;
    private String senha;
    private Long idTipoUsuario;
}

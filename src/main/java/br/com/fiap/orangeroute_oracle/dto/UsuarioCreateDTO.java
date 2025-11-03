package br.com.fiap.orangeroute_oracle.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UsuarioCreateDTO {

    @NotBlank(message = "O nome do usuário é obrigatório.")
    private String nomeUsuario;

    @Email(message = "O e-mail informado não é válido.")
    @NotBlank(message = "O e-mail é obrigatório.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 8, max = 50, message = "A senha deve ter entre 8 e 50 caracteres.")
    private String senha;

    @NotBlank(message = "O tipo de usuário é obrigatório.")
    private Long idTipoUsuario;
}

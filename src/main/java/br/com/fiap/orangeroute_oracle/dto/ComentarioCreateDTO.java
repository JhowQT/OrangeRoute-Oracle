package br.com.fiap.orangeroute_oracle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioCreateDTO {

    private String conteudoComentario;
    private String ativoComentario = "1"; // ativo por padrão
    private Long idUsuario;
    private Long idTrilhaCarreira;
}

package br.com.fiap.orangeroute_oracle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioResponseDTO {

    private Long idComentario;
    private Long idTrilhaCarreira;
    private Long idUsuario;
    private String conteudoComentario;
    private String nomeUsuario;
    private String tituloTrilha;
}

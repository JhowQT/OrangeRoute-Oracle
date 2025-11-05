package br.com.fiap.orangeroute_oracle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoritoResponseDTO {

    private Long idFavorito;
    private String nomeUsuario;
    private String tituloTrilha;
}


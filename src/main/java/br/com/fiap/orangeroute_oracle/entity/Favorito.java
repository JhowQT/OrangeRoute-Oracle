package br.com.fiap.orangeroute_oracle.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_OR_FAVORITO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_favorito")
    private Long idFavorito;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_trilha_carreira", nullable = false)
    private TrilhaCarreira trilhaCarreira;
}

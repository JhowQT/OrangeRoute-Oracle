package br.com.fiap.orangeroute_oracle.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_OR_TRILHA_CARREIRA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrilhaCarreira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trilha_carreira")
    private Long idTrilhaCarreira;

    @Column(name = "tt_trilha_carreira", nullable = false, length = 150)
    private String tituloTrilha;

    @Lob
    @Column(name = "cd_trilha_carreira")
    private String conteudoTrilha;
}


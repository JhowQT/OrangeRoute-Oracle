package br.com.fiap.orangeroute_oracle.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_OR_LINK")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_link")
    private Long idLink;

    @Column(name = "tt_link", nullable = false, length = 150)
    private String tituloLink;

    @Lob
    @Column(name = "cd_link")
    private String conteudoLink;

    @ManyToOne
    @JoinColumn(name = "id_trilha_carreira", nullable = false)
    private TrilhaCarreira trilhaCarreira;
}

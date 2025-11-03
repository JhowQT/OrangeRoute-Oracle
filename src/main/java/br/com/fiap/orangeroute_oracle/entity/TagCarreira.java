package br.com.fiap.orangeroute_oracle.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_OR_TAG_CARREIRA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagCarreira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tag_carreira")
    private Long idTagCarreira;

    @ManyToOne
    @JoinColumn(name = "id_trilha_carreira", nullable = false)
    private TrilhaCarreira trilhaCarreira;

    @ManyToOne
    @JoinColumn(name = "id_tag", nullable = false)
    private Tag tag;
}


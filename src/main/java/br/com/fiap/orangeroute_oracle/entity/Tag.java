package br.com.fiap.orangeroute_oracle.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_OR_TAG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tag")
    private Long idTag;

    @Column(name = "nm_tag", nullable = false, length = 100)
    private String nomeTag;
}

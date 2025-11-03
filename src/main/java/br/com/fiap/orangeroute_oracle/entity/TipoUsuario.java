package br.com.fiap.orangeroute_oracle.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_OR_TIPO_USUARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_usuario")
    private Long idTipoUsuario;

    @Column(name = "nm_tipo_usuario", nullable = false, length = 50)
    private String nomeTipoUsuario;
}

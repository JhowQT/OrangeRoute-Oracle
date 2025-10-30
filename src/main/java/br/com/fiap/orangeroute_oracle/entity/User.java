package br.com.fiap.orangeroute_oracle.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

    private Long id_user;
    private String name;
    private String senha;
    private String email;
    

}

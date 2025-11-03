package br.com.fiap.orangeroute_oracle.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UsuarioFotoDTO {
    private MultipartFile foto;
}

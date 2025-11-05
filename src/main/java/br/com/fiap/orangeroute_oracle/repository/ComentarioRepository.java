package br.com.fiap.orangeroute_oracle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.orangeroute_oracle.entity.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByTrilhaCarreiraIdTrilhaCarreira(Long idTrilhaCarreira);
}

package br.com.fiap.orangeroute_oracle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.orangeroute_oracle.entity.Favorito;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

   List<Favorito> findByUsuarioIdUsuario(Long idUsuario);

    boolean existsByUsuarioIdUsuarioAndTrilhaCarreiraIdTrilhaCarreira(Long idUsuario, Long idTrilhaCarreira);
}

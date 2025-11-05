package br.com.fiap.orangeroute_oracle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.orangeroute_oracle.entity.Favorito;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

    // 🔹 Retorna todos os favoritos de um usuário específico
    List<Favorito> findByUsuarioIdUsuario(Long idUsuario);

    // 🔹 Verifica se o usuário já favoritou uma trilha específica
    boolean existsByUsuarioIdUsuarioAndTrilhaCarreiraIdTrilhaCarreira(Long idUsuario, Long idTrilhaCarreira);
}

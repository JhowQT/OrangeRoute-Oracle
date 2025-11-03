package br.com.fiap.orangeroute_oracle.service;

import br.com.fiap.orangeroute_oracle.entity.Favorito;
import br.com.fiap.orangeroute_oracle.entity.TrilhaCarreira;
import br.com.fiap.orangeroute_oracle.entity.Usuario;
import br.com.fiap.orangeroute_oracle.repository.FavoritoRepository;
import br.com.fiap.orangeroute_oracle.repository.TrilhaCarreiraRepository;
import br.com.fiap.orangeroute_oracle.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TrilhaCarreiraRepository trilhaCarreiraRepository;

    public List<Favorito> listarTodos() {
        return favoritoRepository.findAll();
    }

    public Favorito buscarPorId(Long id) {
        return favoritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Favorito não encontrado com o ID: " + id));
    }

    public Favorito adicionarFavorito(Long idUsuario, Long idTrilha) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + idUsuario));

        TrilhaCarreira trilha = trilhaCarreiraRepository.findById(idTrilha)
                .orElseThrow(() -> new RuntimeException("Trilha não encontrada: " + idTrilha));

        boolean jaExiste = favoritoRepository.findAll().stream()
                .anyMatch(f -> f.getUsuario().getIdUsuario().equals(idUsuario)
                        && f.getTrilhaCarreira().getIdTrilhaCarreira().equals(idTrilha));

        if (jaExiste) {
            throw new RuntimeException("Esta trilha já foi favoritada por este usuário.");
        }

        Favorito favorito = new Favorito();
        favorito.setUsuario(usuario);
        favorito.setTrilhaCarreira(trilha);

        return favoritoRepository.save(favorito);
    }

    public void removerFavorito(Long idUsuario, Long idTrilha) {
        List<Favorito> favoritos = favoritoRepository.findAll().stream()
                .filter(f -> f.getUsuario().getIdUsuario().equals(idUsuario)
                        && f.getTrilhaCarreira().getIdTrilhaCarreira().equals(idTrilha))
                .collect(Collectors.toList());

        if (favoritos.isEmpty()) {
            throw new RuntimeException("Esta trilha não está nos favoritos do usuário.");
        }

        favoritoRepository.deleteAll(favoritos);
    }

    public List<TrilhaCarreira> listarFavoritosPorUsuario(Long idUsuario) {
        return favoritoRepository.findAll().stream()
                .filter(f -> f.getUsuario().getIdUsuario().equals(idUsuario))
                .map(Favorito::getTrilhaCarreira)
                .collect(Collectors.toList());
    }

    public List<Usuario> listarUsuariosPorTrilha(Long idTrilha) {
        return favoritoRepository.findAll().stream()
                .filter(f -> f.getTrilhaCarreira().getIdTrilhaCarreira().equals(idTrilha))
                .map(Favorito::getUsuario)
                .collect(Collectors.toList());
    }
}

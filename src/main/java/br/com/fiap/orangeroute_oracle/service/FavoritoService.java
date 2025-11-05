package br.com.fiap.orangeroute_oracle.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.orangeroute_oracle.dto.FavoritoCreateDTO;
import br.com.fiap.orangeroute_oracle.dto.FavoritoResponseDTO;
import br.com.fiap.orangeroute_oracle.entity.Favorito;
import br.com.fiap.orangeroute_oracle.repository.FavoritoRepository;
import br.com.fiap.orangeroute_oracle.repository.TrilhaCarreiraRepository;
import br.com.fiap.orangeroute_oracle.repository.UsuarioRepository;

@Service
public class FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TrilhaCarreiraRepository trilhaRepository;

    public List<FavoritoResponseDTO> listarTodos() {
        return favoritoRepository.findAll()
                .stream()
                .map(f -> new FavoritoResponseDTO(
                        f.getIdFavorito(),
                        f.getUsuario().getIdUsuario(),
                        f.getTrilhaCarreira().getIdTrilhaCarreira(),
                        f.getUsuario().getNomeUsuario(),
                        f.getTrilhaCarreira().getTituloTrilha()))
                .collect(Collectors.toList());
    }

    public List<FavoritoResponseDTO> listarPorUsuario(Long idUsuario) {
        return favoritoRepository.findByUsuarioIdUsuario(idUsuario)
                .stream()
                .map(f -> new FavoritoResponseDTO(
                        f.getIdFavorito(),
                        f.getUsuario().getIdUsuario(),
                        f.getTrilhaCarreira().getIdTrilhaCarreira(),
                        f.getUsuario().getNomeUsuario(),
                        f.getTrilhaCarreira().getTituloTrilha()))
                .collect(Collectors.toList());
    }

    @Transactional
    public FavoritoResponseDTO criar(FavoritoCreateDTO dto) {

        boolean jaExiste = favoritoRepository.existsByUsuarioIdUsuarioAndTrilhaCarreiraIdTrilhaCarreira(
                dto.getIdUsuario(), dto.getIdTrilhaCarreira());

        if (jaExiste) {
            throw new RuntimeException("Este usuário já favoritou esta trilha!");
        }

        Favorito favorito = new Favorito();
        favorito.setUsuario(usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
        favorito.setTrilhaCarreira(trilhaRepository.findById(dto.getIdTrilhaCarreira())
                .orElseThrow(() -> new RuntimeException("Trilha não encontrada")));

        Favorito salvo = favoritoRepository.save(favorito);

        return new FavoritoResponseDTO(
                salvo.getIdFavorito(),
                salvo.getUsuario().getIdUsuario(),
                salvo.getTrilhaCarreira().getIdTrilhaCarreira(),
                salvo.getUsuario().getNomeUsuario(),
                salvo.getTrilhaCarreira().getTituloTrilha()
        );
    }


    @Transactional
    public void remover(Long idFavorito) {
        favoritoRepository.deleteById(idFavorito);
    }
}

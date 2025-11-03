package br.com.fiap.orangeroute_oracle.service;

import br.com.fiap.orangeroute_oracle.entity.Comentario;
import br.com.fiap.orangeroute_oracle.entity.TrilhaCarreira;
import br.com.fiap.orangeroute_oracle.entity.Usuario;
import br.com.fiap.orangeroute_oracle.repository.ComentarioRepository;
import br.com.fiap.orangeroute_oracle.repository.TrilhaCarreiraRepository;
import br.com.fiap.orangeroute_oracle.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final TrilhaCarreiraRepository trilhaCarreiraRepository;

    public List<Comentario> listarTodos() {
        return comentarioRepository.findAll();
    }

    public Comentario buscarPorId(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentário não encontrado com o ID: " + id));
    }

    public Comentario criarComentario(Long idUsuario, Long idTrilha, Comentario comentario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + idUsuario));

        TrilhaCarreira trilha = trilhaCarreiraRepository.findById(idTrilha)
                .orElseThrow(() -> new RuntimeException("Trilha não encontrada: " + idTrilha));

        comentario.setUsuario(usuario);
        comentario.setTrilhaCarreira(trilha);
        comentario.setAtivoComentario("1"); // ativo por padrão

        return comentarioRepository.save(comentario);
    }

    public Comentario atualizarComentario(Long id, Comentario novoComentario) {
        Comentario existente = buscarPorId(id);
        existente.setConteudoComentario(novoComentario.getConteudoComentario());
        return comentarioRepository.save(existente);
    }

    public void inativarComentario(Long id) {
        Comentario comentario = buscarPorId(id);
        comentario.setAtivoComentario("0");
        comentarioRepository.save(comentario);
    }

    public List<Comentario> listarPorTrilha(Long idTrilha) {
        return comentarioRepository.findAll().stream()
                .filter(c -> c.getTrilhaCarreira().getIdTrilhaCarreira().equals(idTrilha)
                        && "1".equals(c.getAtivoComentario()))
                .collect(Collectors.toList());
    }

    public List<Comentario> listarPorUsuario(Long idUsuario) {
        return comentarioRepository.findAll().stream()
                .filter(c -> c.getUsuario().getIdUsuario().equals(idUsuario)
                        && "1".equals(c.getAtivoComentario()))
                .collect(Collectors.toList());
    }
}

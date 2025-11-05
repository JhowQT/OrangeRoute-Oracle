package br.com.fiap.orangeroute_oracle.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.orangeroute_oracle.dto.ComentarioCreateDTO;
import br.com.fiap.orangeroute_oracle.dto.ComentarioResponseDTO;
import br.com.fiap.orangeroute_oracle.entity.Comentario;
import br.com.fiap.orangeroute_oracle.repository.ComentarioRepository;
import br.com.fiap.orangeroute_oracle.repository.TrilhaCarreiraRepository;
import br.com.fiap.orangeroute_oracle.repository.UsuarioRepository;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TrilhaCarreiraRepository trilhaRepository;

    // 🔹 Listar todos os comentários
    public List<ComentarioResponseDTO> listarTodos() {
        return comentarioRepository.findAll()
                .stream()
                .map(c -> new ComentarioResponseDTO(
                        c.getIdComentario(),
                        c.getTrilhaCarreira().getIdTrilhaCarreira(),
                        c.getUsuario().getIdUsuario(),
                        c.getConteudoComentario(),
                        c.getUsuario().getNomeUsuario(),
                        c.getTrilhaCarreira().getTituloTrilha()))
                .collect(Collectors.toList());
    }

    // 🔹 Listar comentários de uma trilha específica
    public List<ComentarioResponseDTO> listarPorTrilha(Long idTrilha) {
        return comentarioRepository.findByTrilhaCarreiraIdTrilhaCarreira(idTrilha)
                .stream()
                .map(c -> new ComentarioResponseDTO(
                        c.getIdComentario(),
                        c.getTrilhaCarreira().getIdTrilhaCarreira(),
                        c.getUsuario().getIdUsuario(),
                        c.getConteudoComentario(),
                        c.getUsuario().getNomeUsuario(),
                        c.getTrilhaCarreira().getTituloTrilha()))
                .collect(Collectors.toList());
    }

    // 🔹 Criar novo comentário (com FKs)
    @Transactional
    public ComentarioResponseDTO criar(ComentarioCreateDTO dto) {
        Comentario comentario = new Comentario();

        comentario.setConteudoComentario(dto.getConteudoComentario());
        comentario.setAtivoComentario("1");

        comentario.setUsuario(usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")));

        comentario.setTrilhaCarreira(trilhaRepository.findById(dto.getIdTrilhaCarreira())
                .orElseThrow(() -> new RuntimeException("Trilha não encontrada")));

        Comentario salvo = comentarioRepository.save(comentario);

        return new ComentarioResponseDTO(
                salvo.getIdComentario(),
                salvo.getTrilhaCarreira().getIdTrilhaCarreira(),
                salvo.getUsuario().getIdUsuario(),
                salvo.getConteudoComentario(),
                salvo.getUsuario().getNomeUsuario(),
                salvo.getTrilhaCarreira().getTituloTrilha()
        );
    }
}

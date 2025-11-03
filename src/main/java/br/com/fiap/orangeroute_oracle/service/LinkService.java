package br.com.fiap.orangeroute_oracle.service;

import br.com.fiap.orangeroute_oracle.entity.Link;
import br.com.fiap.orangeroute_oracle.entity.TrilhaCarreira;
import br.com.fiap.orangeroute_oracle.repository.LinkRepository;
import br.com.fiap.orangeroute_oracle.repository.TrilhaCarreiraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepository linkRepository;
    private final TrilhaCarreiraRepository trilhaCarreiraRepository;

    public List<Link> listarTodos() {
        return linkRepository.findAll();
    }

    public Link buscarPorId(Long id) {
        return linkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Link não encontrado com o ID: " + id));
    }

    public List<Link> listarPorTrilha(Long idTrilha) {
        return linkRepository.findAll().stream()
                .filter(l -> l.getTrilhaCarreira().getIdTrilhaCarreira().equals(idTrilha))
                .collect(Collectors.toList());
    }

    public Link criarLink(Long idTrilha, Link link) {
        TrilhaCarreira trilha = trilhaCarreiraRepository.findById(idTrilha)
                .orElseThrow(() -> new RuntimeException("Trilha de carreira não encontrada: " + idTrilha));

        validarTituloEConteudo(link);

        link.setTrilhaCarreira(trilha);
        return linkRepository.save(link);
    }

    public Link atualizarLink(Long id, Link novoLink) {
        Link existente = buscarPorId(id);

        if (novoLink.getTituloLink() != null) {
            existente.setTituloLink(novoLink.getTituloLink());
        }
        if (novoLink.getConteudoLink() != null) {
            existente.setConteudoLink(novoLink.getConteudoLink());
        }

        validarTituloEConteudo(existente);
        return linkRepository.save(existente);
    }

    public void excluir(Long id) {
        if (!linkRepository.existsById(id)) {
            throw new RuntimeException("Link não encontrado para exclusão.");
        }
        linkRepository.deleteById(id);
    }

    private void validarTituloEConteudo(Link link) {
        if (link.getTituloLink() == null || link.getTituloLink().trim().isEmpty()) {
            throw new RuntimeException("O título do link é obrigatório.");
        }
        if (link.getConteudoLink() == null || link.getConteudoLink().trim().isEmpty()) {
            throw new RuntimeException("O conteúdo (URL) do link é obrigatório.");
        }
        if (!link.getConteudoLink().startsWith("http")) {
            throw new RuntimeException("O link deve começar com http ou https.");
        }
    }
}

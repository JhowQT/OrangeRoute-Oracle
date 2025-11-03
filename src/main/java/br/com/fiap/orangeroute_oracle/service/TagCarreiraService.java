package br.com.fiap.orangeroute_oracle.service;

import br.com.fiap.orangeroute_oracle.entity.Tag;
import br.com.fiap.orangeroute_oracle.entity.TagCarreira;
import br.com.fiap.orangeroute_oracle.entity.TrilhaCarreira;
import br.com.fiap.orangeroute_oracle.repository.TagCarreiraRepository;
import br.com.fiap.orangeroute_oracle.repository.TagRepository;
import br.com.fiap.orangeroute_oracle.repository.TrilhaCarreiraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagCarreiraService {

    private final TagCarreiraRepository tagCarreiraRepository;
    private final TagRepository tagRepository;
    private final TrilhaCarreiraRepository trilhaCarreiraRepository;

    public List<TagCarreira> listarTodos() {
        return tagCarreiraRepository.findAll();
    }

    public TagCarreira buscarPorId(Long id) {
        return tagCarreiraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Associação Tag-Carreira não encontrada com o ID: " + id));
    }

    public TagCarreira vincularTagATrilha(Long idTrilha, Long idTag) {
        TrilhaCarreira trilha = trilhaCarreiraRepository.findById(idTrilha)
                .orElseThrow(() -> new RuntimeException("Trilha não encontrada: " + idTrilha));

        Tag tag = tagRepository.findById(idTag)
                .orElseThrow(() -> new RuntimeException("Tag não encontrada: " + idTag));

        boolean jaExiste = tagCarreiraRepository.findAll().stream()
                .anyMatch(tc -> tc.getTrilhaCarreira().getIdTrilhaCarreira().equals(idTrilha)
                        && tc.getTag().getIdTag().equals(idTag));

        if (jaExiste) {
            throw new RuntimeException("Esta tag já está vinculada a esta trilha.");
        }

        TagCarreira novaRelacao = new TagCarreira();
        novaRelacao.setTrilhaCarreira(trilha);
        novaRelacao.setTag(tag);
        return tagCarreiraRepository.save(novaRelacao);
    }

    public void desvincularTagDaTrilha(Long idTrilha, Long idTag) {
        List<TagCarreira> relacoes = tagCarreiraRepository.findAll().stream()
                .filter(tc -> tc.getTrilhaCarreira().getIdTrilhaCarreira().equals(idTrilha)
                        && tc.getTag().getIdTag().equals(idTag))
                .collect(Collectors.toList());

        if (relacoes.isEmpty()) {
            throw new RuntimeException("A tag não está vinculada a esta trilha.");
        }

        tagCarreiraRepository.deleteAll(relacoes);
    }

    public List<Tag> buscarTagsPorTrilha(Long idTrilha) {
        return tagCarreiraRepository.findAll().stream()
                .filter(tc -> tc.getTrilhaCarreira().getIdTrilhaCarreira().equals(idTrilha))
                .map(TagCarreira::getTag)
                .collect(Collectors.toList());
    }

    public List<TrilhaCarreira> buscarTrilhasPorTag(Long idTag) {
        return tagCarreiraRepository.findAll().stream()
                .filter(tc -> tc.getTag().getIdTag().equals(idTag))
                .map(TagCarreira::getTrilhaCarreira)
                .collect(Collectors.toList());
    }
}

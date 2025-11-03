package br.com.fiap.orangeroute_oracle.service;

import br.com.fiap.orangeroute_oracle.entity.Tag;
import br.com.fiap.orangeroute_oracle.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public List<Tag> listarTodos() {
        return tagRepository.findAll();
    }

    public Tag buscarPorId(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag não encontrada com o ID: " + id));
    }

    public Tag cadastrar(Tag tag) {
        validarNomeObrigatorio(tag.getNomeTag());
        validarDuplicidadeNome(tag.getNomeTag(), null);
        return tagRepository.save(tag);
    }

    public Tag atualizar(Long id, Tag novaTag) {
        Tag existente = buscarPorId(id);

        if (novaTag.getNomeTag() != null) {
            validarNomeObrigatorio(novaTag.getNomeTag());
            validarDuplicidadeNome(novaTag.getNomeTag(), id);
            existente.setNomeTag(novaTag.getNomeTag());
        }

        return tagRepository.save(existente);
    }

    public void excluir(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new RuntimeException("Tag não encontrada para exclusão.");
        }
        tagRepository.deleteById(id);
    }

    private void validarNomeObrigatorio(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("O nome da tag é obrigatório.");
        }
        if (nome.length() > 100) {
            throw new RuntimeException("O nome da tag deve ter no máximo 100 caracteres.");
        }
    }

    private void validarDuplicidadeNome(String nome, Long idAtual) {
        boolean duplicado = tagRepository.findAll().stream()
                .anyMatch(t -> t.getNomeTag() != null
                        && t.getNomeTag().equalsIgnoreCase(nome)
                        && (idAtual == null || !t.getIdTag().equals(idAtual)));

        if (duplicado) {
            throw new RuntimeException("Já existe uma tag cadastrada com este nome.");
        }
    }
}

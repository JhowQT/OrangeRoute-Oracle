package br.com.fiap.orangeroute_oracle.service;

import br.com.fiap.orangeroute_oracle.entity.TrilhaCarreira;
import br.com.fiap.orangeroute_oracle.repository.TrilhaCarreiraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrilhaCarreiraService {

    private final TrilhaCarreiraRepository trilhaCarreiraRepository;

    public List<TrilhaCarreira> listarTodos() {
        return trilhaCarreiraRepository.findAll();
    }

    public TrilhaCarreira buscarPorId(Long id) {
        return trilhaCarreiraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trilha de carreira não encontrada com o ID: " + id));
    }

    public TrilhaCarreira cadastrar(TrilhaCarreira trilha) {
        validarTituloObrigatorioELimite(trilha.getTituloTrilha());
        validarDuplicidadeTitulo(trilha.getTituloTrilha(), null);
        return trilhaCarreiraRepository.save(trilha);
    }

    public TrilhaCarreira atualizar(Long id, TrilhaCarreira novaTrilha) {
        TrilhaCarreira existente = buscarPorId(id);

        if (novaTrilha.getTituloTrilha() != null) {
            validarTituloObrigatorioELimite(novaTrilha.getTituloTrilha());
            validarDuplicidadeTitulo(novaTrilha.getTituloTrilha(), id);
            existente.setTituloTrilha(novaTrilha.getTituloTrilha());
        }

        if (novaTrilha.getConteudoTrilha() != null) {
            existente.setConteudoTrilha(novaTrilha.getConteudoTrilha());
        }

        return trilhaCarreiraRepository.save(existente);
    }

    public void excluir(Long id) {
        if (!trilhaCarreiraRepository.existsById(id)) {
            throw new RuntimeException("Trilha de carreira não encontrada para exclusão.");
        }
        trilhaCarreiraRepository.deleteById(id);
    }


    private void validarTituloObrigatorioELimite(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new RuntimeException("O título da trilha é obrigatório.");
        }
        if (titulo.length() > 150) {
            throw new RuntimeException("O título da trilha deve ter no máximo 150 caracteres.");
        }
    }

    private void validarDuplicidadeTitulo(String titulo, Long idAtual) {
        boolean duplicado = trilhaCarreiraRepository.findAll().stream()
                .anyMatch(t -> t.getTituloTrilha() != null
                        && t.getTituloTrilha().equalsIgnoreCase(titulo)
                        && (idAtual == null || !t.getIdTrilhaCarreira().equals(idAtual)));

        if (duplicado) {
            throw new RuntimeException("Já existe uma trilha com este título.");
        }
    }
}

package br.com.fiap.orangeroute_oracle.service;

import br.com.fiap.orangeroute_oracle.entity.TipoUsuario;
import br.com.fiap.orangeroute_oracle.repository.TipoUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TipoUsuarioService {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    // Listar todos os tipos de usuário
    public List<TipoUsuario> listarTodos() {
        return tipoUsuarioRepository.findAll();
    }

    // Buscar tipo de usuário por ID
    public TipoUsuario buscarPorId(Long id) {
        return tipoUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado com o ID: " + id));
    }

    // Criar um novo tipo de usuário
    public TipoUsuario cadastrar(TipoUsuario tipoUsuario) {
        boolean nomeExistente = tipoUsuarioRepository.findAll().stream()
                .anyMatch(t -> t.getNomeTipoUsuario().equalsIgnoreCase(tipoUsuario.getNomeTipoUsuario()));

        if (nomeExistente) {
            throw new RuntimeException("Já existe um tipo de usuário com este nome.");
        }

        return tipoUsuarioRepository.save(tipoUsuario);
    }

    // Atualizar tipo de usuário
    public TipoUsuario atualizar(Long id, TipoUsuario novoTipo) {
        TipoUsuario tipo = buscarPorId(id);
        tipo.setNomeTipoUsuario(novoTipo.getNomeTipoUsuario());
        return tipoUsuarioRepository.save(tipo);
    }

    // Excluir tipo de usuário
    public void excluir(Long id) {
        if (!tipoUsuarioRepository.existsById(id)) {
            throw new RuntimeException("Tipo de usuário não encontrado para exclusão.");
        }
        tipoUsuarioRepository.deleteById(id);
    }
}

package br.com.fiap.orangeroute_oracle.service;

import br.com.fiap.orangeroute_oracle.entity.TipoUsuario;
import br.com.fiap.orangeroute_oracle.entity.Usuario;
import br.com.fiap.orangeroute_oracle.repository.TipoUsuarioRepository;
import br.com.fiap.orangeroute_oracle.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }

    public Usuario cadastrar(Usuario usuario) {
       
        Optional<Usuario> emailExistente = usuarioRepository.findAll().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(usuario.getEmail()))
                .findFirst();

        if (emailExistente.isPresent()) {
            throw new RuntimeException("Já existe um usuário cadastrado com este e-mail.");
        }

        if (usuario.getSenha() == null || usuario.getSenha().length() < 8) {
            throw new RuntimeException("A senha deve ter pelo menos 8 caracteres.");
        }

        if (usuario.getAtivo() == null) {
            usuario.setAtivo("1");
        }

        Long idTipo = usuario.getTipoUsuario().getIdTipoUsuario();
        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(idTipo)
                .orElseThrow(() -> new RuntimeException("Tipo de usuário inválido: " + idTipo));

        usuario.setTipoUsuario(tipoUsuario);
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario novoUsuario) {
        Usuario existente = buscarPorId(id);

        if (novoUsuario.getNomeUsuario() != null) {
            existente.setNomeUsuario(novoUsuario.getNomeUsuario());
        }

        if (novoUsuario.getEmail() != null) {
            existente.setEmail(novoUsuario.getEmail());
        }

        if (novoUsuario.getSenha() != null && novoUsuario.getSenha().length() >= 8) {
            existente.setSenha(novoUsuario.getSenha());
        }

        if (novoUsuario.getAtivo() != null) {
            existente.setAtivo(novoUsuario.getAtivo());
        }

        if (novoUsuario.getTipoUsuario() != null) {
            Long idTipo = novoUsuario.getTipoUsuario().getIdTipoUsuario();
            TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(idTipo)
                    .orElseThrow(() -> new RuntimeException("Tipo de usuário inválido: " + idTipo));
            existente.setTipoUsuario(tipoUsuario);
        }

        return usuarioRepository.save(existente);
    }

    public void excluir(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado para exclusão.");
        }
        usuarioRepository.deleteById(id);
    }
}

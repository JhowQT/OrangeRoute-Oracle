package br.com.fiap.orangeroute_oracle.oracledao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class UsuarioDAO {

    @Autowired
    private DataSource dataSource;

    public void inserirUsuario(String nome, String email, String senha, String ativo, int idTipoUsuario) {
        String procedure = "{call prc_insere_usuario(?, ?, ?, ?, ?)}";

        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            stmt.setString(4, ativo);
            stmt.setInt(5, idTipoUsuario);
            stmt.execute();

            System.out.println("INSERT de usuário executado: " + nome);

        } catch (SQLException e) {
            System.err.println("Erro INSERT (Usuário): " + e.getMessage());
        }
    }

    public void atualizarUsuario(int id, String nome, String email, String senha, String ativo, int idTipoUsuario) {
        String procedure = "{call prc_update_usuario(?, ?, ?, ?, ?, ?)}";

        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setInt(1, id);
            stmt.setString(2, nome);
            stmt.setString(3, email);
            stmt.setString(4, senha);
            stmt.setString(5, ativo);
            stmt.setInt(6, idTipoUsuario);
            stmt.execute();

            System.out.println("UPDATE de usuário executado (ID: " + id + ")");

        } catch (SQLException e) {
            System.err.println("Erro UPDATE (Usuário): " + e.getMessage());
        }
    }

    public void deletarUsuario(int id) {
        String procedure = "{call prc_delete_usuario(?)}";

        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("DELETE de usuário executado (ID: " + id + ")");

        } catch (SQLException e) {
            System.err.println("Erro DELETE (Usuário): " + e.getMessage());
        }
    }
}

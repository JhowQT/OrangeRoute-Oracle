package br.com.fiap.orangeroute_oracle.oracledao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class TipoUsuarioDAO {

    @Autowired
    private DataSource dataSource; 

    public void inserirTipoUsuario(String nomeTipo) {
        String procedure = "{call prc_insere_tipo_usuario(?)}";

        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setString(1, nomeTipo);
            stmt.execute();

            System.out.println("INSERT executado com sucesso: " + nomeTipo);

        } catch (SQLException e) {
            System.err.println("Erro ao inserir tipo de usuário: " + e.getMessage());
        }
    }

    public void atualizarTipoUsuario(int id, String nomeNovo) {
        String procedure = "{call prc_update_tipo_usuario(?, ?)}";

        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setInt(1, id);
            stmt.setString(2, nomeNovo);
            stmt.execute();

            System.out.println("UPDATE executado com sucesso (ID: " + id + ")");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar tipo de usuário: " + e.getMessage());
        }
    }

    public void deletarTipoUsuario(int id) {
        String procedure = "{call prc_delete_tipo_usuario(?)}";

        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("DELETE executado com sucesso (ID: " + id + ")");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar tipo de usuário: " + e.getMessage());
        }
    }
}

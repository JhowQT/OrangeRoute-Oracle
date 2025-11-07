package br.com.fiap.orangeroute_oracle.oracledao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class LinkDAO {

    @Autowired
    private DataSource dataSource;

    public void inserirLink(String titulo, String conteudo, int idTrilhaCarreira) {
        String procedure = "{call prc_insere_link(?, ?, ?)}";
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setString(1, titulo);
            stmt.setString(2, conteudo);
            stmt.setInt(3, idTrilhaCarreira);
            stmt.execute();

            System.out.println("INSERT executado (Link): " + titulo + " → Trilha " + idTrilhaCarreira);

        } catch (SQLException e) {
            System.err.println("Erro INSERT (Link): " + e.getMessage());
        }
    }

    public void atualizarLink(int idLink, String titulo, String conteudo, int idTrilhaCarreira) {
        String procedure = "{call prc_update_link(?, ?, ?, ?)}";
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setInt(1, idLink);
            stmt.setString(2, titulo);
            stmt.setString(3, conteudo);
            stmt.setInt(4, idTrilhaCarreira);
            stmt.execute();

            System.out.println("UPDATE executado (Link ID: " + idLink + ")");

        } catch (SQLException e) {
            System.err.println("Erro UPDATE (Link): " + e.getMessage());
        }
    }

    public void deletarLink(int idLink) {
        String procedure = "{call prc_delete_link(?)}";
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setInt(1, idLink);
            stmt.execute();

            System.out.println("DELETE executado (Link ID: " + idLink + ")");

        } catch (SQLException e) {
            System.err.println("Erro DELETE (Link): " + e.getMessage());
        }
    }
}

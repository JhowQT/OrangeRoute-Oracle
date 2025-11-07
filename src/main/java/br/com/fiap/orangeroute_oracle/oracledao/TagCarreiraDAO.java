package br.com.fiap.orangeroute_oracle.oracledao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class TagCarreiraDAO {

    @Autowired
    private DataSource dataSource;

    public void inserirTagCarreira(int idTrilhaCarreira, int idTag) {
        String procedure = "{call prc_insere_tag_carreira(?, ?)}";
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setInt(1, idTrilhaCarreira);
            stmt.setInt(2, idTag);
            stmt.execute();

            System.out.println("INSERT executado (TagCarreira): Trilha " + idTrilhaCarreira + " → Tag " + idTag);

        } catch (SQLException e) {
            System.err.println("Erro INSERT (TagCarreira): " + e.getMessage());
        }
    }

    public void atualizarTagCarreira(int idTagCarreira, int idTrilhaCarreira, int idTag) {
        String procedure = "{call prc_update_tag_carreira(?, ?, ?)}";
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setInt(1, idTagCarreira);
            stmt.setInt(2, idTrilhaCarreira);
            stmt.setInt(3, idTag);
            stmt.execute();

            System.out.println("UPDATE executado (TagCarreira ID: " + idTagCarreira + ")");

        } catch (SQLException e) {
            System.err.println("Erro UPDATE (TagCarreira): " + e.getMessage());
        }
    }

    public void deletarTagCarreira(int idTagCarreira) {
        String procedure = "{call prc_delete_tag_carreira(?)}";
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setInt(1, idTagCarreira);
            stmt.execute();

            System.out.println("DELETE executado (TagCarreira ID: " + idTagCarreira + ")");

        } catch (SQLException e) {
            System.err.println("Erro DELETE (TagCarreira): " + e.getMessage());
        }
    }
}

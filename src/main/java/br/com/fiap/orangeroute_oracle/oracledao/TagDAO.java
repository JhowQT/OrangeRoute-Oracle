package br.com.fiap.orangeroute_oracle.oracledao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class TagDAO {

    @Autowired
    private DataSource dataSource;

    public void inserirTag(String nomeTag) {
        String procedure = "{call prc_insere_tag(?)}";
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setString(1, nomeTag);
            stmt.execute();

            System.out.println("INSERT executado (Tag): " + nomeTag);

        } catch (SQLException e) {
            System.err.println("Erro INSERT (Tag): " + e.getMessage());
        }
    }

    public void atualizarTag(int idTag, String nomeTag) {
        String procedure = "{call prc_update_tag(?, ?)}";
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setInt(1, idTag);
            stmt.setString(2, nomeTag);
            stmt.execute();

            System.out.println("UPDATE executado (Tag ID: " + idTag + ")");

        } catch (SQLException e) {
            System.err.println("Erro UPDATE (Tag): " + e.getMessage());
        }
    }

    public void deletarTag(int idTag) {
        String procedure = "{call prc_delete_tag(?)}";
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setInt(1, idTag);
            stmt.execute();

            System.out.println("DELETE executado (Tag ID: " + idTag + ")");

        } catch (SQLException e) {
            System.err.println("Erro DELETE (Tag): " + e.getMessage());
        }
    }
}

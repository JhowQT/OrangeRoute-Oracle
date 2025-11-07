package br.com.fiap.orangeroute_oracle.oracledao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class TrilhaCarreiraDAO {

    @Autowired
    private DataSource dataSource;

    public void inserirTrilhaCarreira(String titulo, String conteudo) {
        String procedure = "{call prc_insere_trilha_carreira(?, ?)}";
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setString(1, titulo);
            stmt.setString(2, conteudo);
            stmt.execute();

            System.out.println("INSERT executado: " + titulo);

        } catch (SQLException e) {
            System.err.println("Erro INSERT (TrilhaCarreira): " + e.getMessage());
        }
    }


    public void atualizarTrilhaCarreira(int id, String titulo, String conteudo) {
        String procedure = "{call prc_update_trilha_carreira(?, ?, ?)}";
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setInt(1, id);
            stmt.setString(2, titulo);
            stmt.setString(3, conteudo);
            stmt.execute();

            System.out.println("UPDATE executado (ID: " + id + ")");

        } catch (SQLException e) {
            System.err.println("Erro UPDATE (TrilhaCarreira): " + e.getMessage());
        }
    }


    public void deletarTrilhaCarreira(int id) {
        String procedure = "{call prc_delete_trilha_carreira(?)}";
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("DELETE executado (ID: " + id + ")");

        } catch (SQLException e) {
            System.err.println("Erro DELETE (TrilhaCarreira): " + e.getMessage());
        }
    }
}

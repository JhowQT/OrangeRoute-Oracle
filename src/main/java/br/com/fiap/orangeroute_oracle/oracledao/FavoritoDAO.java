package br.com.fiap.orangeroute_oracle.oracledao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class FavoritoDAO {

    @Autowired
    private DataSource dataSource;

    public void inserirFavorito(int idUsuario, int idTrilhaCarreira) {
        String procedure = "{call prc_insere_favorito(?, ?)}";
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idTrilhaCarreira);
            stmt.execute();

            System.out.println("INSERT executado (Favorito): Usuário " + idUsuario + " → Trilha " + idTrilhaCarreira);

        } catch (SQLException e) {
            System.err.println("Erro INSERT (Favorito): " + e.getMessage());
        }
    }

    public void atualizarFavorito(int idFavorito, int idUsuario, int idTrilhaCarreira) {
        String procedure = "{call prc_update_favorito(?, ?, ?)}";
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setInt(1, idFavorito);
            stmt.setInt(2, idUsuario);
            stmt.setInt(3, idTrilhaCarreira);
            stmt.execute();

            System.out.println("UPDATE executado (Favorito ID: " + idFavorito + ")");

        } catch (SQLException e) {
            System.err.println("Erro UPDATE (Favorito): " + e.getMessage());
        }
    }

    public void deletarFavorito(int idFavorito) {
        String procedure = "{call prc_delete_favorito(?)}";
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

            stmt.setInt(1, idFavorito);
            stmt.execute();

            System.out.println("DELETE executado (Favorito ID: " + idFavorito + ")");

        } catch (SQLException e) {
            System.err.println("Erro DELETE (Favorito): " + e.getMessage());
        }
    }
}

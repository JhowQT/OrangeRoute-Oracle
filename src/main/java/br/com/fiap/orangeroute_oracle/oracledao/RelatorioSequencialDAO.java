package br.com.fiap.orangeroute_oracle.oracledao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class RelatorioSequencialDAO {

    @Autowired
    private DataSource dataSource;

    public String executarRelatorioSequencial() {

        String sql = "{ call prc_relatorio_sequencial(?) }";

        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.registerOutParameter(1, Types.CLOB);

            stmt.execute();

            Clob clob = stmt.getClob(1);

            String resultado = null;

            if (clob != null) {
                resultado = clob.getSubString(1, (int) clob.length());
            }

            System.out.println("\n==============================================");
            System.out.println(" PROCEDURE: RELATÓRIO SEQUENCIAL");
            System.out.println("==============================================");

            if (resultado != null && !resultado.isEmpty()) {
                System.out.println(resultado);
            } else {
                System.out.println("Nenhum resultado retornado.");
            }

            return resultado;

        } catch (SQLException e) {

            String erro = e.getMessage();

            if (erro.contains("-20070")) {
                System.err.println("Regra de negócio: Dados insuficientes (mínimo 5 registros).");
            } else {
                System.err.println("Erro ao executar relatório sequencial: " + erro);
            }

            return null;
        }
    }
}
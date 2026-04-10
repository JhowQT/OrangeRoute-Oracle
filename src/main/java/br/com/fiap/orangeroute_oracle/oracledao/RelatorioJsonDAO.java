package br.com.fiap.orangeroute_oracle.oracledao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

@Component
public class RelatorioJsonDAO {

    @Autowired
    private DataSource dataSource;

    public String executarRelatorioJson() {

        String sql = "{call prc_relatorio_json(?)}";

        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.registerOutParameter(1, Types.CLOB);

            stmt.execute();

            Reader reader = stmt.getCharacterStream(1);
            StringBuilder resultado = new StringBuilder();

            if (reader != null) {
                BufferedReader br = new BufferedReader(reader);
                String linha;

                while ((linha = br.readLine()) != null) {
                    resultado.append(linha);
                }
            }

            System.out.println("\n==============================================");
            System.out.println(" RELATÓRIO JSON");
            System.out.println("==============================================");
            System.out.println(resultado.toString());

            return resultado.toString();

        } catch (Exception e) {

            System.err.println("Erro ao executar relatório JSON: " + e.getMessage());
            return null;
        }
    }
}
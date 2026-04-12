package br.com.fiap.orangeroute_oracle.executor;

import br.com.fiap.orangeroute_oracle.OrangerouteOracleApplication;
import br.com.fiap.orangeroute_oracle.oracledao.ComentarioJsonDAO;
import br.com.fiap.orangeroute_oracle.oracledao.RelatorioJsonDAO;
import br.com.fiap.orangeroute_oracle.oracledao.ScoreTrilhaDAO;
import br.com.fiap.orangeroute_oracle.oracledao.RelatorioSequencialDAO;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner; 

public class MainExecutor {

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("   EXECUÇÃO DAS FUNÇÕES E PROCEDURES ORACLE   ");
        System.out.println("==============================================\n");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu usuário Oracle: ");
        String user = scanner.nextLine().trim();

        System.out.print("Digite sua senha Oracle: ");
        String password = scanner.nextLine().trim();

        System.setProperty("DB_USER", user);
        System.setProperty("DB_PASSWORD", password);

        System.out.println("\nConectando ao banco...\n");

        ConfigurableApplicationContext context = new SpringApplicationBuilder(OrangerouteOracleApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        ComentarioJsonDAO comentarioJsonDAO = context.getBean(ComentarioJsonDAO.class);
        RelatorioJsonDAO relatorioJsonDAO = context.getBean(RelatorioJsonDAO.class);
        ScoreTrilhaDAO scoreTrilhaDAO = context.getBean(ScoreTrilhaDAO.class);
        RelatorioSequencialDAO relatorioSequencialDAO = context.getBean(RelatorioSequencialDAO.class);

        try {

            System.out.println("==============================================");
            System.out.println(" FUNÇÃO: LISTA JSON DE COMENTÁRIOS");
            System.out.println("==============================================\n");

            comentarioJsonDAO.listarComentariosJson();

            System.out.println("\n==============================================");
            System.out.println(" PROCEDURE: RELATÓRIO JSON");
            System.out.println("==============================================\n");

            relatorioJsonDAO.executarRelatorioJson();

            System.out.println("\n==============================================");
            System.out.println(" FUNÇÃO: SCORE DA TRILHA");
            System.out.println("==============================================\n");

            scoreTrilhaDAO.calcularScore(1);

            System.out.println("\n==============================================");
            System.out.println(" PROCEDURE: RELATÓRIO SEQUENCIAL");
            System.out.println("==============================================\n");

            relatorioSequencialDAO.executarRelatorioSequencial();

            System.out.println("\n==============================================");
            System.out.println(" EXECUÇÃO FINALIZADA COM SUCESSO ");
            System.out.println("==============================================\n");

        } catch (Exception e) {

            System.err.println("Erro durante execução: " + e.getMessage());

        } finally {
            context.close();
        }
    }
}
package br.com.fiap.orangeroute_oracle.executor;

import br.com.fiap.orangeroute_oracle.OrangerouteOracleApplication;
import br.com.fiap.orangeroute_oracle.oracledao.UsuarioDAO;
import br.com.fiap.orangeroute_oracle.oracledao.ComentarioDAO;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

public class MainExecutor {

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("   INICIANDO EXECUÇÃO MANUAL DAS PROCEDURES   ");
        System.out.println("==============================================\n");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite seu usuário Oracle (ex: rmXXXXXX): ");
        String user = scanner.nextLine().trim();

        System.out.print("Digite sua senha Oracle: ");
        String password = scanner.nextLine().trim();

        System.setProperty("DB_USER", user);
        System.setProperty("DB_PASSWORD", password);

        System.out.println("\nCredenciais Oracle definidas com sucesso!");
        System.out.println("Conectando e executando procedures...\n");

        ConfigurableApplicationContext context = new SpringApplicationBuilder(OrangerouteOracleApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        UsuarioDAO usuarioDAO = context.getBean(UsuarioDAO.class);
        ComentarioDAO comentarioDAO = context.getBean(ComentarioDAO.class);

        try {

            System.out.println("==============================================");
            System.out.println(" TESTE DE PROCEDURES: USUÁRIO");
            System.out.println("==============================================\n");

            /*System.out.println("Executando INSERT de usuário...");
            usuarioDAO.inserirUsuario(
                    "Usuário Sprint 2",
                    "sprint2usuario@fiap.com",
                    "senha123",
                    "1",
                    1
            );*/

            System.out.println("\n Executando UPDATE de usuário...");
            usuarioDAO.atualizarUsuario(
                    13,
                    "Usuário Sprint 2 Atualizado",
                    "atualizado@fiap.com",
                    "novasenha123",
                    "1",
                    1
            );

            /*System.out.println("\nExecutando DELETE de usuário...");
            usuarioDAO.deletarUsuario(13);*/

            System.out.println("\n Procedures de USUÁRIO executadas com sucesso!\n");

            System.out.println("==============================================");
            System.out.println(" TESTE DE PROCEDURES: COMENTÁRIO");
            System.out.println("==============================================\n");

            /*System.out.println("Executando INSERT de comentário...");
            comentarioDAO.inserirComentario(
                    "Comentário de teste via DAO - Sprint 2",
                    "1",
                    13,
                    2 
            );*/

            System.out.println("\nExecutando UPDATE de comentário...");
            comentarioDAO.atualizarComentario(
                    1,
                    "Comentário atualizado via DAO - Sprint 2",
                    "1",
                    27,
                    2
            );

            /*System.out.println("\nExecutando DELETE de comentário...");
            comentarioDAO.deletarComentario(27);*/

            System.out.println("\nProcedures de COMENTÁRIO executadas com sucesso!\n");

            System.out.println("TODAS AS PROCEDURES EXECUTADAS COM SUCESSO!");

        } catch (Exception e) {
            System.err.println("\n Erro durante execução das procedures: " + e.getMessage());
        } finally {
            context.close();
        }

        System.out.println("\n==============================================");
        System.out.println("EXECUÇÃO FINALIZADA COM SUCESSO");
        System.out.println("==============================================\n");
    }
}

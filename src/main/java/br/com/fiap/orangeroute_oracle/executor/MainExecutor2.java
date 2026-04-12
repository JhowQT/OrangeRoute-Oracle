package br.com.fiap.orangeroute_oracle.executor;

import br.com.fiap.orangeroute_oracle.OrangerouteOracleApplication;
import br.com.fiap.orangeroute_oracle.oracledao.UsuarioDAO;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

public class MainExecutor2 {

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

        System.out.println("\nConectando ao banco...\n");

        ConfigurableApplicationContext context = new SpringApplicationBuilder(OrangerouteOracleApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        UsuarioDAO usuarioDAO = context.getBean(UsuarioDAO.class);

        try {

            System.out.println("==============================================");
            System.out.println(" TESTE DE PROCEDURES: USUÁRIO");
            System.out.println("==============================================\n");

            System.out.println("Executando INSERT de usuário...");
            usuarioDAO.inserirUsuario(
                    "Teste ORACLE Java",
                    "oracle@fiap.com",
                    "12345678",
                    "1",
                    1
            );


            System.out.println("\nExecutando DELETE de usuário...");
            usuarioDAO.deletarUsuario(6);

            System.out.println("\nProcedures de USUÁRIO executadas com sucesso!\n");

        } catch (Exception e) {

            System.err.println("\nErro durante execução: " + e.getMessage());

        } finally {
            context.close();
        }

        System.out.println("\n==============================================");
        System.out.println("EXECUÇÃO FINALIZADA");
        System.out.println("==============================================\n");
    }
}
package br.com.fiap.orangeroute_oracle.executor;

import br.com.fiap.orangeroute_oracle.oracledao.UsuarioDAO;
import br.com.fiap.orangeroute_oracle.OrangerouteOracleApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

public class MainExecutor {

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("🔶 INICIANDO EXECUÇÃO MANUAL DAS PROCEDURES 🔶");
        System.out.println("==============================================\n");

        // 🔐 Solicita as credenciais Oracle
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite seu usuário Oracle (ex: rmXXXXXX): ");
        String user = scanner.nextLine().trim();

        System.out.print("Digite sua senha Oracle: ");
        String password = scanner.nextLine().trim();

        // Define as credenciais como variáveis de ambiente para o Spring Boot
        System.setProperty("DB_USER", user);
        System.setProperty("DB_PASSWORD", password);

        System.out.println("\nCredenciais Oracle definidas com sucesso!");
        System.out.println("Conectando e executando procedures...\n");

        // 🔧 Inicializa o contexto Spring SEM servidor web
        ConfigurableApplicationContext context = new SpringApplicationBuilder(OrangerouteOracleApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        UsuarioDAO usuarioDAO = context.getBean(UsuarioDAO.class);

        try {
            /*System.out.println("➡️ Executando INSERT...");
            usuarioDAO.inserirUsuario("Usuario Teste DAO", "usuariodao@fiap.com", "123456789", "1", 2);*/

            //System.out.println("➡️ Executando UPDATE...");
            //usuarioDAO.atualizarUsuario(9, "DAO Usuario Teste", "daousuario@fiap.com", "987654321", "1", 1);

            System.out.println("➡️ Executando DELETE...");
            usuarioDAO.deletarUsuario(9); 

            System.out.println("\n✅ Procedures executadas com sucesso!");

        } catch (Exception e) {
            System.err.println("❌ Erro durante execução das procedures: " + e.getMessage());
        } finally {
            context.close();
        }

        System.out.println("\n==============================================");
        System.out.println("✅ EXECUÇÃO FINALIZADA");
        System.out.println("==============================================\n");
    }
}

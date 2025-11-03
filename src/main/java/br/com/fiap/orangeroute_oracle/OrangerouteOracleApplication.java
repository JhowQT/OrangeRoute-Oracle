package br.com.fiap.orangeroute_oracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication
public class OrangerouteOracleApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu usuário Oracle (ex: rmXXXXXX): ");
        String user = scanner.nextLine().trim();

        System.out.print("Digite sua senha Oracle: ");
        String password = scanner.nextLine().trim();

        System.setProperty("DB_USER", user);
        System.setProperty("DB_PASSWORD", password);

        System.out.println("\nCredenciais Oracle definidas com sucesso!");
        System.out.println("Iniciando aplicação OrangeRoute-Oracle...\n");

        SpringApplication.run(OrangerouteOracleApplication.class, args);
    }
}

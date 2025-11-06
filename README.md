# OrangeRoute-Oracle

## Tecnologias Usadas

<div style="display: inline_block"><br> 
  <img aling="center" src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>
</div>

## INTEGRANTES
### Jhonantan Quispe Torrez
[![Linkedin](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white
)](https://www.linkedin.com/in/jhonatan-quispe-torrez-360b60198/)[![Github](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/JhowQT)
### Julia Damasceno Busso
[![Linkedin](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white
)](https://www.linkedin.com/in/jhonatan-quispe-torrez-360b60198/)[![Github](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/JhowQT)
### Gabriel Gomes
[![Linkedin](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white
)](https://www.linkedin.com/in/jhonatan-quispe-torrez-360b60198/)[![Github](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/JhowQT)

**Jhonatan Quispe Torrez — Java & Banco de Dados**

`Responsável pelo backend em Java/Spring Boot e pela modelagem relacional no Oracle. Implementou Entities com JPA/Hibernate, Services/Repositories e endpoints REST, além da configuração de persistência, testes de API (Postman) e documentação Swagger.`

**Gabriel Gomes — Advanced Business Development with .NET & DevOps/Cloud**

`Responsável pelo desenho de arquitetura .NET (camadas, DTOs, Repositórios) e boas práticas de Clean Architecture. No pilar DevOps & Cloud, cuidou de Docker/Docker Compose, organização de CI/CD, e provisionamento/ajustes de infraestrutura em nuvem para deploy e observabilidade.`

**Julia Bussinos — Mobile App Development & Compliance & QA**

`Responsável pelo app mobile (React Native/Expo), navegação entre telas, integração com a API e protótipo funcional. No pilar Compliance & Quality Assurance, estruturou a documentação de escopo/visão, critérios de qualidade, e evidências para validação das entregas.`
_____________________________________________________________________________________________________
🧩 Visão Geral

Criado com o propósito de auxiliar pessoas interessadas no universo da programação, o Orange Route tem como objetivo apresentar as principais tendências do mercado de tecnologia e as áreas que um programador deve conhecer.
A plataforma oferece guias e trilhas personalizadas que ajudam o usuário a escolher as matérias e caminhos de aprendizado mais adequados ao seu perfil.
Assim, o projeto atua como um facilitador para quem deseja trilhar seus primeiros passos no mundo dos códigos e da inovação tecnológica.

______________________________________________________________________________________________________

<details>
  <summary>📘 MER - Modelo Entidade Relacionamento</summary>

  ![MER](https://github.com/JhowQT/OrangeRoute-Oracle/issues/1#issue-3598042952)

  _Figura: MER do sistema._
</details>

<details>
  <summary>📗 DER - Diagrama Entidade Relacionamento</summary>

  ![MER](https://github.com/JhowQT/OrangeRoute-Oracle/issues/2#issue-3598052468)

  _Figura: DER do sistema._
</details>



A OrangeRoute API fornece endpoints para gerenciamento de:

Usuários

Trilhas de Carreira

Comentários

Favoritos

Tags e TagPost (relação entre tags e posts)

Tipos de Usuário

Arquitetura em camadas Controller → Service → Repository → Entity, com JPA/Hibernate.

### COMO USAR OS ENDPOINTS

- **USUARIOS** -
> -
> - Busca todos os usuarios **GET**`localhost:8080:usuarios` 
> - Busca usuarios por id **GET**`localhost:8080:usuarios/{id}` 
> - Cria um usuario **POST**`localhost:8080:usuarios`
> - Deleta por ID **DELETE**`localhost:8080:usuarios/{id}`
> -

_______________________________________________________________________________________________________

- **TIPO - USUARIOS** -
> -
> - Busca todos os tipo-usuarios **GET**`localhost:8080:tipos-usuario` 
> - Busca tipo-usuarios por id **GET**`localhost:8080:tipos-usuario/{id}` 
> - Cria um tipo-usuario **POST**`localhost:8080:tipos-usuario`
> - Deleta por ID **DELETE**`localhost:8080:tipos-usuario/{id}`
> -

_______________________________________________________________________________________________________

- **TRILHA DE CARREIRA** -
> -
> - Busca todos as trilhas **GET**`localhost:8080:trilhas` 
> - Cria uma trilha **POST**`localhost:8080:trilhas`
> -

_______________________________________________________________________________________________________

- **FAVORITOS** -
> -
> - Busca todos os favoritoss **GET**`localhost:8080:favoritos`  
> - Cria um favoritos **POST**`localhost:8080:favotritos`
> - Deleta por ID **DELETE**`localhost:8080:favoritos/{id}`
> -

_______________________________________________________________________________________________________

- **COMENTARIOS** -
> -
> - Busca todos os comentarios **GET**`localhost:8080:comentarios` 
> - Cria um comentarios **POST**`localhost:8080:comentarios`
> - Deleta por ID **DELETE**`localhost:8080:comentarios/{id}`
> -

_______________________________________________________________________________________________________

- **TAG** -
> -
> - Busca todos os tags **GET**`localhost:8080:tags` 
> - Cria um tags **POST**`localhost:8080:tags`
> - Deleta por ID **DELETE**`localhost:8080:tags/{id}`
> -

_______________________________________________________________________________________________________

- **TAG - POST** -
> -
> - Busca todos os tags **GET**`localhost:8080:tags-post` 
> - Cria um tags **POST**`localhost:8080:tags-post`
> -

________________________________________________________________________________________________________

📚 Documentação dos Endpoints (Swagger/OpenAPI)

Observação: Swagger ainda será analisado e integrado. Assim que habilitar:

UI: http://localhost:8080/swagger-ui/index.html

OpenAPI JSON: http://localhost:8080/v3/api-docs

________________________________________________________________________________________________________

🧪 Coleções de Teste (Postman/Insomnia)

Coleção Postman: docs/OrangeRoute API.postman_collection.json

Ambiente Postman: docs/base_url.postman_environment.json

base_url = http://localhost:8080

Como usar:

Importe a coleção e o ambiente.

Selecione o ambiente base_url.

Execute os requests ({{base_url}}/usuarios, etc.).

Inclua prints com 200/201/204 e corpos JSON na pasta docs/.

___________________________________________________________________________________________________________

🧪 Como Executar Localmente
Pré-requisitos

JDK 17+

Maven 3.9+

Oracle Database (acesso e credenciais)

Configuração de Banco

Crie/edite src/main/resources/application.properties:

spring.datasource.url=jdbc:oracle:thin:@//<host>:<port>/<service_name>
spring.datasource.username= `rm560601`
spring.datasource.password=`040301`
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.Oracle12cDialect
server.port=8080

Executando
# Clonar o repositório
git clone https://github.com/JhowQT/Oracle-Orange-Route.git
cd <repo-orangeroute>

# Rodar a aplicação
mvn spring-boot:run
# ou
mvn clean package && java -jar target/orangeroute-*.jar


Aplicação disponível em:
http://localhost:8080

__________________________________________________________________________________________________________
## LINK YT
https://youtu.be/aRWhoVAMbdw



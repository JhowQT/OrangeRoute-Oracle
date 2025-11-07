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

  ![DER](https://github.com/JhowQT/OrangeRoute-Oracle/issues/2#issue-3598052468)

  _Figura: DER do sistema._
</details>



## A OrangeRoute API fornece endpoints para gerenciamento de:

``TIPO_USUARIO``
``USUARIO``
``TRILHA_CARREIRA``
``TAG_CARREIRA``
``TAG``
``FAVORITO``
``COMENTARIO``
``LINK``

-- *Arquitetura em camadas Controller → Service → Repository → Entity, com JPA/Hibernate.*

### COMO USAR OS ENDPOINTS

- **USUARIO** -
> -
> - Busca todos os usuarios **GET**`localhost:8080:usuario` 
> - Busca usuarios por id **GET**`localhost:8080:usuario/{id}` 
> - Cria um usuario **POST**`localhost:8080:usuario`
> - Atualiza por ID **PUT**`localhost:8080:usuario/{id}`
> - Atualiza foto **PUT**`localhost:8080:usuario/{id}/foto`
> - Deleta por ID **DELETE**`localhost:8080:usuario/{id}`
> - `IdTipoUsuario: 1 para admin e 2 para user, apenas esses dois dados são aceitos para tipo_usuario`
> 
#### 📥 Corpo da Requisição POST (JSON esperado)
```json
{
  "nomeUsuario": "Thaisa Mendes",
  "email": "thaisa.mendes@example.com",
  "senha": "123456",
  "idTipoUsuario": 2
}
```
_______________________________________________________________________________________________________

- **TIPO - USUARIOS** -
> -
> - Busca todos os tipo-usuarios **GET**`localhost:8080:tipos-usuario` 
> - Busca tipo-usuarios por id **GET**`localhost:8080:tipo-usuario/{id}` 
> -

_______________________________________________________________________________________________________

- **TRILHA DE CARREIRA** -
> -
> - Busca todos as trilhas **GET**`localhost:8080:trilhas` 
> - Busca trilha por ID **GET**`localhost:8080:trilhas/{id}`
> -

_______________________________________________________________________________________________________

- **FAVORITOS** -
> -
> - Busca todos os favoritoss **GET**`localhost:8080:favoritos`  
> - Busca favoritos ID de usuario **GET**`localhost:8080:favotritos/usuario/{idUsuario}`
> - Cria favorito **POST**`localhost:8080:favoritos`
> - Deleta por ID **DELETE**`localhost:8080:favotiros/{idFavorito}`
> -

#### 📥 Corpo da Requisição POST (JSON esperado)
```json
{
  "idUsuario": 2,
  "idTrilhaCarreira": 5
}
```
_______________________________________________________________________________________________________

- **COMENTARIOS** -
> -
> - Busca todos os comentarios **GET**`localhost:8080:comentarios` 
> - Busca ID de Trilha **GET**`localhost:8080:comentarios/trilha/{idTrilha}`
> - Cria Comentario **POST**`localhost:8080:comentarios`
> -

#### 📥 Corpo da Requisição POST (JSON esperado)
```json
{
  "idUsuario": 2,
  "idTrilhaCarreira": 5,
  "conteudoComentario": "Gostei muito das dicas sobre Java e mercado de trabalho!"
}
```
_______________________________________________________________________________________________________

- **TAG** -
> -
> - Busca todos os tags **GET**`localhost:8080:tags` 
> - Busca ID **GET**`localhost:8080:tags/{id}`
> - Busca ID por Trilha **GET**`localhost:8080:tags/trilha/{idTrilha}`
> -

_______________________________________________________________________________________________________

- **LINK** -
> -
> - Busca todos os tags **GET**`localhost:8080:links` 
> - Busca por ID **GET**`localhost:8080:links/{id}`
> - Busca Id por Trilha **GET**`localhost:8080:links/trilha/{idTrilha}`
> -

________________________________________________________________________________________________________

- **LOGIN** -
> -
> - Post Login **GET**`localhost:8080:auth/login` 
> -
#### 📥 Corpo da Requisição POST (JSON esperado)
```json
{
  "email": "thaisa.mendes@example.com",
  "senha": "123456"
}
```
________________________________________________________________________________________________________

📚 Documentação dos Endpoints (Swagger/OpenAPI)

Observação: Swagger ainda será analisado e integrado. Assim que habilitar:

UI: http://localhost:8080/swagger-ui/index.html

OpenAPI JSON: http://localhost:8080/v3/api-docs

________________________________________________________________________________________________________

🧪 Coleções de Teste (Postman/Insomnia)

Coleção Postman: docs/OrangeRoute API.postman_collection.json
Ambiente Postman: docs/base_url.postman_environment.json

`exports fornecidos dentro do ZIP do arquivo`

base_url = http://localhost:8080
base_id = 2

Como usar:

Importe a coleção e o ambiente.
Selecione o ambiente base_url.
Execute os requests ({{base_url}}/usuarios, etc.).
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
- git clone https://github.com/JhowQT/OrangeRoute-Oracle
- cd OrangeRoute-oracle
- code .


# Rodar a aplicação
mvn spring-boot:run
# ou
mvn clean package && java -jar target/orangeroute-*.jar

## ACESSO AO BD
- Para iniciar o projeto insira o acesso
- campo de login **rm560601**
- campo de senha **040301**

Aplicação disponível em:
- http://localhost:8080/ `aqui colocar as rotas detalhas no topo para acessar os dados do end-points`
- http://localhost:8080//swagger-ui/index.html `vizualização de todas as rotas no swagger`

__________________________________________________________________________________________________________
## LINK YT
- https://youtu.be/affdkYrqqnU



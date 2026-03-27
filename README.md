# Compliance Check API

### Backend para Análise de Reputação de Figuras Públicas — Challenge Plusoft | FIAP
Solução backend desenvolvida em parceria com a Plusoft para ajudar empresas a analisar a reputação de influenciadores e figuras públicas antes de fechar contratos de publicidade, reduzindo riscos de imagem e protegendo investimentos em marketing.

## Tecnologias

| Tecnologia                     | Descrição                                      |
|--------------------------------|-----------------------------------------------|
| Java 21                        | Linguagem principal                           |
| Spring Boot 3                  | Framework base da aplicação                   |
| Spring Security + JWT          | Autenticação e autorização por token          |
| Spring Data JPA / Hibernate    | Persistência e mapeamento ORM                 |
| Spring Web                     | Camada REST                                  |
| Oracle Database                | Banco de dados relacional                     |
| Maven                          | Gerenciamento de dependências                 |
| Swagger / OpenAPI              | Documentação dos endpoints                    |

# Arquitetura

A API segue o padrão REST com separação em camadas:

| Camada        | Responsabilidade Técnica                                      | Exemplo de Uso                          |
|--------------|---------------------------------------------------------------|----------------------------------------|
| controller    | Exposição de endpoints REST e tratamento de requisições HTTP  | @RestController, @RequestMapping       |
| service       | Implementação das regras de negócio                           | validações, lógica de domínio          |
| repository    | Comunicação com o banco via JPA                               | JpaRepository, queries                 |
| model         | Representação das entidades e mapeamento ORM                  | @Entity, @Table                        |
| dto           | Transporte de dados entre camadas                             | Request/Response DTOs                  |
| security      | Configuração de autenticação e autorização                    | JWT, filtros, SecurityConfig           |

O fluxo de autenticação utiliza JWT (JSON Web Token): o cliente realiza login e recebe um token, que deve ser enviado no header Authorization: Bearer <token> nas requisições protegidas.

# Como rodar o projeto

## Pré-requisitos
- Java 21
- Maven
- Oracle Database (ou ajuste o application.properties para H2 em memória para testes)

````
  # 1. Clone o repositório
git clone https://github.com/seu-usuario/seu-repositorio.git

  # 2. Acessa a pasta do projeto
cd plusoft-challenge-java

  # 3. Configure o banco de dados em src/main/resources/application.properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

  # 4. Execute a aplicação
mvn spring-boot:run
````

# Acessando a documentação Swagger

````
htthp://localhost:8080/swagger-ui/index.html
````

# Endpoints

## Autenticação

| Método | Rota                         | Descrição                                      |
|--------|------------------------------|-----------------------------------------------|
| POST   | /usuarios/publico/login      | Login do usuário — retorna JWT                |
| GET    | /usuarios/publico/listar     | Listagem pública de usuários                  |
| GET    | /usuarios/detalhes           | Detalhes do usuário autenticado               |

## Clientes

| Método | Rota                         | Descrição                               |
|--------|------------------------------|------------------------------------------|
| POST   | /clientes/publico/cadastrar  | Cadastro de novo cliente                |
| GET    | /clientes/detalhes           | Detalhes do cliente autenticado         |
| PUT    | /clientes                   | Atualização de dados do cliente         |

## Pesquisas

| Método | Rota                         | Descrição                                      |
|--------|------------------------------|-----------------------------------------------|
| POST   | /pesquisas                   | Cria nova pesquisa de figura pública          |
| GET    | /pesquisas                   | Lista pesquisas do usuário autenticado        |
| GET    | /pesquisas/em-andamento      | Lista pesquisas em andamento                  |
| GET    | /pesquisas/{id}              | Busca pesquisa por ID                         |
| PUT    | /pesquisas/{id}              | Adiciona figura pública à pesquisa            |
| DELETE | /pesquisas/{id}              | Define pesquisa como concluída                |

## Diagramas :page_facing_up:
### DER - Diagrama de Entidade-Relacionamento
![DER](https://github.com/oRonold/plusoft-challenge-java/assets/109552047/b27e3dab-668a-44fe-a816-7fca62232b6a)

### UML -  Diagrama de Classes
![plusoft_challenge_class](https://github.com/oRonold/plusoft-challenge-java/assets/109552047/0eb33b92-b0a6-47c9-a60c-8aecdfd84135)

# Time

| Integrante                     | Contribuição                                             |
|--------------------------------|----------------------------------------------------------|
| Ronald de Oliveira Farias      | Backend Java — API REST, Spring Security, JWT, Oracle    |
| Gustavo Carvalho Noia          | Compliance & Quality Assurance                           |
| Vitor Teixeira Silva           | IoT e IA Generativa                                      |
| Lucas Serbato de Barros        | Mobile (React Native) e .NET                            |

#### Projeto desenvolvido durante o segundo ano na FIAP como parte do programa de Challenges com empresas parceiras.

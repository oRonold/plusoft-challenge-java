# API Pesquisa de Análise de Sentimento de Celebridades

## Integrantes e suas respectivas matérias
<p>Ronald de Oliveira Farias - RM 552364 - Java Advanced, Mastering Relational and Non-Relational Database.</p>
<p>Gustavo Carvalho Noia - RM 552410 - Advanced Business Development With .NET, Compliance & Quality Assurance.</p>
<p>Vitor Teixeira - RM 552228 - Disruptive Architectures: IOT, IOB & Generative AI, Devops Tools e Cloud Computing.</p>
<p>Lucas Serbato - RM 551821 - Mobile App Development.</p>

## Como utilizar :scroll:
- Download do arquivo ZIP da aplicação no botão CODE destacado em verde.
- Descompactar e abrir o projeto em alguma IDE, preferencialmente, Intellij.
- As dependências serão baixadas automaticamente pelo Maven.
- Iniciar a aplicação no botão play no topo da IDE.
- Abrir aplicação de testes de API(Insomnia, Postman) disponível em seu computador.
- Colocar a URL: "http://localhost:8080/" após o barra colocar o endpoint escolhido para teste de acordo com a documentação.

## Diagramas :page_facing_up:
Diagrama de Entidade-Relacionamento
![DER_sprint1](https://github.com/oRonold/sprint1-java/assets/109552047/52a00321-a9aa-4bc8-9dd3-297947d2e3b5)

Diagrama de Classe de Entidades
![Diagrama de Classe](https://github.com/oRonold/sprint1-java/assets/109552047/481c2fa2-b87a-43dd-82aa-d273dbd30164)

## Vídeo :clapper:
Link do Video para Proposta Tecnológica: https://www.youtube.com/watch?v=dfO73PmUA74

## Endpoints :mag:
### Pesquisa-Controller
- POST - pesquisa/cadastrar 
- GET - pesquisa/listar
- GET - pesquisa/listar/{id}
- PUT - pesquisa/atualizar
- DELETE - pesquisa/excluir{id}

### FiguraPublica-Controller
- POST - figura/cadastrar 
- GET - figura/listar
- GET - figura/listar/{id}
- PUT - figura/atualizar
- DELETE - figura/excluir{id}

### TipoServico-Controller
- POST - servico/cadastrar 
- GET - servico/listar
- GET - servico/listar/{id}
- PUT - servico/atualizar
- DELETE - servico/excluir{id}

### Usuario-Controller
- POST - usuario/cadastrar
- GET - usuario/listar
- GET - usuario/listar/{id}
- PUT - usuario/atualizar
- DELETE - usuario/excluir{id}

### Cliente-Controller
- POST - cliente/cadastrar
- GET - cliente/listar
- GET - cliente/listar/{id}
- PUT - cliente/atualizar
- DELETE - cliente/excluir{id}

### Ramo-Controller
- POST - ramo/cadastrar
- GET - ramo/listar
- GET - ramo/listar/{id}
- PUT - ramo/atualizar
- DELETE - ramo/excluir{id}

### EnderecoCliente-Controller
- POST - endereco/cadastrar
- GET - endereco/listar
- GET - endereco/listar/{id}
- PUT - endereco/atualizar
- DELETE - endereco/excluir{id}

### Logradouro-Controller
- POST - endereco/cadastrar
- GET - endereco/listar
- GET - endereco/listar/{id}
- PUT - endereco/atualizar
- DELETE - endereco/excluir{id}

### Bairro-Controller
- POST - bairro/cadastrar
- GET - bairro/listar
- GET - bairro/listar/{id}
- PUT - bairro/atualizar
- DELETE - bairro/excluir{id}

### Cidade-Controller
- POST - cidade/cadastrar
- GET - cidade/listar
- GET - cidade/listar/{id}
- PUT - cidade/atualizar
- DELETE - cidade/excluir{id}

### Estado-Controller
- POST - estado/cadastrar
- GET - estado/listar
- GET - estado/listar/{id}
- PUT - estado/atualizar
- DELETE - estado/excluir{id}

### Pais-Controller
- POST - pais/cadastrar
- GET - pais/listar
- GET - pais/listar/{id}
- PUT - pais/atualizar
- DELETE - pais/excluir{id}

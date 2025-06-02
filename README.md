[![Licença MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.0-blue.svg?logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)

# 🎬 MovieFlix API

Uma plataforma desenvolvida com **Spring Boot** para gerenciar um catálogo completo de filmes, categorias e serviços de streaming, além de um sistema de autenticação seguro.

---

## ✨ Visão Geral

A MovieFlix API oferece um conjunto de **endpoints RESTful** para permitir a criação, leitura, atualização e exclusão (**CRUD**) de filmes, categorias e streamings, além de um sistema de autenticação baseado em **JWT** (JSON Web Tokens) para controle de acesso.

---

## 🛠️ Tecnologias Utilizadas

* ⚪ **Spring Boot**: Framework para construção rápida de aplicações Java.
* ⚪ **Spring Security**: Para autenticação e autorização robustas, incluindo JWT.
* ⚪ **Spring Data JPA**: Para fácil interação com o banco de dados.
* ⚪ **PostgreSQL**: Banco de dados relacional.
* ⚪ **Lombok**: Redução de boilerplate code.
* ⚪ **SpringDoc OpenAPI (Swagger UI)**: Para documentação interativa da API.
* ⚪ **Maven**: Ferramenta de automação de build.
* ⚪ **Flyway**: Migrations dos dados.

---

## 🚀 Como Iniciar o Projeto

Siga os passos abaixo para configurar e rodar a API localmente:

### ⚙️ Pré-requisitos:

* Java 17+
* Maven
* PostgreSQL
* Sua IDE favorita (IntelliJ IDEA, VS Code, Eclipse)

### 💾 Clone o Repositório:


git clone https://github.com/seu-usuario/movieflix-api.git # Substitua pelo link do seu repositório
cd movieflix-api


### ⚙️ Configuração do Banco de Dados:

  * Certifique-se de ter uma instância do **PostgreSQL** rodando.
  * Crie um banco de dados chamado `movieflix_db` (ou o nome que preferir).
  * Configure as credenciais do banco de dados no arquivo `src/main/resources/application.properties` ou `application.yml`:


* spring.datasource.url=jdbc:postgresql://localhost:5432/movieflix_db
* spring.datasource.username=seu_usuario_postgres
* spring.datasource.password=sua_senha_postgres
* spring.jpa.hibernate.ddl-auto=update # Ou create, dependendo da sua necessidade


### 🔑 Configuração do Secret JWT:

  * Defina uma chave secreta para a geração e validação de JWTs no seu `application.properties`:


movieFlix.security.secret=SEU_SECRET_AQUI_UMA_STRING_LONGA_E_SEGURA


**Dica**: Use uma string longa e complexa para maior segurança.

### 📦 Build do Projeto:


mvn clean install


### ▶️ Executar a Aplicação:


mvn spring-boot:run


A API estará disponível em `http://localhost:8080`.

---

## 🏛️ Diagrama da Arquitetura

Para uma melhor compreensão da estrutura e fluxo do sistema, observe o diagrama de arquitetura da MovieFlix API. Ele ilustra as principais camadas e a interação entre os componentes.


<img src="/src/main/java/br/com/movieflix/assets/diagrama.png" alt="Diagrama da Arquitetura" width="70%">


---

## API 🌐 Endpoints (Swagger UI)

A MovieFlix API é documentada interativamente via **Swagger UI**, que facilita a exploração e o teste de todos os endpoints diretamente do seu navegador.

Acesse a documentação em: `http://localhost:8080/swagger-ui.html`


<img src="/src/main/java/br/com/movieflix/assets/swagger.png" alt="Documentação API - Swagger UI" width="90%">


### 📍 Resumo dos Endpoints Principais:

#### 🔐 Autenticação (`/movieflix/auth`)

* **POST** `/movieflix/auth/register`: Registra um novo usuário na plataforma.
* **POST** `/movieflix/auth/login`: Realiza o login do usuário, retornando um token JWT para acesso às rotas protegidas.

#### 🎬 Filmes (`/movieflix/movie`)

* **GET** `/movieflix/movie`: Lista todos os filmes cadastrados.
* **GET** `/movieflix/movie/{id}`: Busca um filme específico pelo ID.
* **GET** `/movieflix/movie/search?category={id}`: Filtra filmes por categoria.
* **POST** `/movieflix/movie`: Insere um novo filme.
* **PUT** `/movieflix/movie/{id}`: Atualiza um filme existente pelo ID.
* **DELETE** `/movieflix/movie/{id}`: Deleta um filme pelo ID.

#### 🗂️ Categorias (`/movieflix/category`)

* **GET** `/movieflix/category`: Lista todas as categorias de filmes.
* **GET** `/movieflix/category/{id}`: Busca uma categoria específica pelo ID.
* **POST** `/movieflix/category`: Insere uma nova categoria.
* **DELETE** `/movieflix/category/{id}`: Deleta uma categoria pelo ID.

#### 📺 Streamings (`/movieflix/streaming`)

* **GET** `/movieflix/streaming`: Lista todas as streamings cadastradas.
* **GET** `/movieflix/streaming/{id}`: Busca uma streaming específica pelo ID.
* **POST** `/movieflix/streaming`: Insere uma nova streaming.
* **DELETE** `/movieflix/streaming/{id}`: Deleta uma streaming pelo ID.

---

## 🛡️ Segurança

A API utiliza **JSON Web Tokens (JWT)** para autenticação.

  * Para acessar a maioria dos endpoints (exceto `/auth/register` | `/auth/login` | `/api/api-docs/**` | `/swagger/**`), você precisará incluir o token JWT obtido no login no cabeçalho `Authorization` da requisição, no formato `Bearer`.
  * **Exemplo no Postman/Insomnia**: `Authorization: Bearer <seu_token_jwt>`

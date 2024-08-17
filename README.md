# Auth API

Esta API de autenticação foi desenvolvida utilizando Spring Boot, Spring Security, JPA, JWT e PostgreSQL. A API permite o cadastro e login de usuários, autenticação via tokens JWT, definição de roles (admin e basic) e proteção de rotas com base na autenticação e roles dos usuários.

## Funcionalidades

- **Cadastro de Usuários**: Os usuários podem se registrar na aplicação.
- **Login de Usuários**: Os usuários registrados podem fazer login e receber um token JWT.
- **Autenticação JWT**: A API utiliza tokens JWT para autenticar as requisições.
- **Roles de Usuários**: Suporte a roles de admin e basic, com diferentes permissões.
- **Rotas Protegidas**: Algumas rotas são protegidas por autenticação e outras são restritas por roles específicas.

## Tecnologias Utilizadas

- **Spring Boot**: Framework principal para construção da API.
- **Spring Security**: Usado para gerenciar a autenticação e autorização.
- **JWT (JSON Web Tokens)**: Utilizado para autenticação baseada em tokens.
- **JPA (Java Persistence API)**: Utilizado para persistência de dados.
- **PostgreSQL**: Banco de dados utilizado para armazenar as informações dos usuários.

## Configuração

### 1. Pré-requisitos

- **Java 17** ou superior
- **Maven 3.8** ou superior
- **PostgreSQL** instalado e em execução

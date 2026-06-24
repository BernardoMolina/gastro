# Guia de Deploy — Sistema Clínica Gastro

Este documento descreve como implantar o sistema completo (frontend Next.js +
backend Spring Boot + banco PostgreSQL) usando Docker e Docker Compose.

## 1. Visão geral da arquitetura

O sistema é composto por três serviços orquestrados pelo `docker-compose.yml`:

| Serviço    | Tecnologia       | Porta (host) | Descrição                                  |
|------------|------------------|--------------|--------------------------------------------|
| `db`       | PostgreSQL 16    | `5432`       | Banco de dados relacional                  |
| `backend`  | Spring Boot (Java 17) | `8080`  | API REST, autenticação JWT                 |
| `frontend` | Next.js (Node 20)| `3000`       | Interface web (médicos, pacientes, etc.)   |

Fluxo de comunicação:

- O **navegador** acessa o frontend em `http://localhost:3000`.
- O **navegador** chama a API do backend diretamente em
  `http://localhost:8080/clinica-gastro` (as requisições são feitas no lado do
  cliente, com o token JWT no `localStorage`). O sufixo `/clinica-gastro` é o
  `server.servlet.context-path` definido no backend.
- O **backend** acessa o banco pelo nome interno da rede Docker: `db:5432`.

## 2. Pré-requisitos

- [Docker](https://docs.docker.com/get-docker/) 24+ instalado
- [Docker Compose](https://docs.docker.com/compose/) v2+ (já incluído no Docker Desktop)
- Os dois repositórios clonados (frontend e backend)

## 3. Estrutura de pastas esperada

Como frontend e backend ficam em repositórios separados, clone os dois lado a lado:

```
projetos/
├── clinica-backend/      <- repositório do Spring Boot (docker-compose.yml fica AQUI)
│   ├── pom.xml
│   ├── mvnw / .mvn
│   ├── Dockerfile.backend
│   ├── docker-compose.yml  <- execute daqui
│   ├── DEPLOY.md
│   └── src/
└── clinica-frontend/     <- repositório do Next.js
    ├── Dockerfile
    └── ...
```
## 4. Subindo a aplicação

Na **pasta do backend** (onde está o `docker-compose.yml`):

```bash
# Constrói as imagens e sobe os três serviços
docker compose up --build
```

A ordem de inicialização é controlada automaticamente:

1. `db` sobe e aguarda ficar saudável (`pg_isready`).
2. `backend` só inicia depois que o banco está pronto (Flyway roda as migrations no startup).
3. `frontend` inicia após o backend subir.

Para rodar em segundo plano (modo destacado):

```bash
docker compose up --build -d
```

## 5. Acessando o sistema

| Recurso          | URL                                      |
|------------------|------------------------------------------|
| Aplicação web    | http://localhost:3000                    |
| API (backend)    | http://localhost:8080/clinica-gastro     |
| Banco PostgreSQL | localhost:5432 (db: `teste4`)            |



## 6. Usuarios : TODAS AS SENHAS SAO 123456

- **Secretaria:** usuario5@example.com -- usuario6@example.com
- **Medico:** usuario1@example.com -- usuario2@example.com
- **Paciente:** usuario3@example.com -- usuario4@example.com


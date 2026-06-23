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
├── clinica-frontend/     <- este repositório (contém Dockerfile, docker-compose.yml, DEPLOY.md)
│   ├── Dockerfile
│   ├── Dockerfile.backend <- copie este arquivo para o repositório do backend
│   ├── docker-compose.yml
│   └── ...
└── clinica-backend/      <- repositório do Spring Boot
    ├── pom.xml
    ├── mvnw / .mvn
    ├── src/
    └── Dockerfile.backend <- cole aqui (copiado do frontend)
```

> Se você usar outro nome/caminho para a pasta do backend, ajuste a variável
> `BACKEND_PATH` (veja a seção 5).

## 4. Preparando o backend

1. **Copie o `Dockerfile.backend`** deste repositório para a **raiz do
   repositório do backend** (onde está o `pom.xml`).

2. **Garanta que o `application.properties` leia variáveis de ambiente.** O
   `docker-compose.yml` injeta as configurações de banco via variáveis. Confirme
   que o arquivo `src/main/resources/application.properties` está assim:

   ```properties
   spring.application.name=clinica-gastro
   server.servlet.context-path=/clinica-gastro

   spring.datasource.url=${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/teste4}
   spring.datasource.username=${SPRING_DATASOURCE_USERNAME:postgres}
   spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:postgres}
   spring.datasource.driver-class-name=org.postgresql.Driver
   server.port=${SERVER_PORT:8080}
   ```

   A sintaxe `${VARIAVEL:valor_padrao}` faz o Spring usar a variável de ambiente
   quando disponível (dentro do Docker) e o valor padrão quando rodar localmente.
   Apenas a `url`, `username` e `password` precisam virar variáveis — o restante
   (context-path, logs) pode permanecer fixo como já está.

   > **Atenção (Flyway):** o projeto usa **Flyway** para versionar o schema do
   > banco (as migrations em `src/main/resources/db/migration` rodam no startup).
   > Por isso **não** defina `spring.jpa.hibernate.ddl-auto=update` — deixe o
   > Flyway ser o único responsável pelo schema, evitando conflito/drift. O
   > `docker-compose.yml` já segue essa regra (não injeta `ddl-auto`).

3. **Driver do PostgreSQL.** Já presente no seu `pom.xml` (`org.postgresql`),
   nenhuma alteração necessária.

4. **Healthcheck (opcional).** O projeto **não** inclui o
   `spring-boot-starter-actuator`, então o `docker-compose.yml` vem **sem**
   healthcheck no backend (o frontend chama a API pelo navegador em runtime e
   não precisa esperar o backend ficar "healthy"). Se quiser um healthcheck,
   adicione ao `pom.xml`:

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-actuator</artifactId>
   </dependency>
   ```

   e reative o bloco `healthcheck:` que está comentado no `docker-compose.yml`.

## 5. Configuração (variáveis de ambiente)

As configurações já têm valores padrão no `docker-compose.yml`. Para
personalizar, crie um arquivo `.env` na mesma pasta do `docker-compose.yml`:

```env
# Caminho do repositório do backend (relativo ao docker-compose.yml)
BACKEND_PATH=../clinica-backend

# URL pública da API (acessada pelo navegador), incluindo o context-path.
# Em produção, troque pelo domínio real, ex.: https://api.suaclinica.com/clinica-gastro
NEXT_PUBLIC_API_URL=http://localhost:8080/clinica-gastro
```

As credenciais do banco (`POSTGRES_USER`, `POSTGRES_PASSWORD`, `POSTGRES_DB`)
estão definidas no `docker-compose.yml` e podem ser alteradas lá. Se mudar,
atualize também as variáveis `SPRING_DATASOURCE_*` do serviço `backend`.

## 6. Subindo a aplicação

Na pasta do frontend (onde está o `docker-compose.yml`):

```bash
# Constrói as imagens e sobe os três serviços
docker compose up --build
```

A ordem de inicialização é controlada automaticamente:

1. `db` sobe e aguarda ficar saudável (`pg_isready`).
2. `backend` só inicia depois que o banco está pronto.
3. `frontend` só inicia depois que o backend está saudável.

Para rodar em segundo plano (modo destacado):

```bash
docker compose up --build -d
```

## 7. Acessando o sistema

| Recurso          | URL                                      |
|------------------|------------------------------------------|
| Aplicação web    | http://localhost:3000                    |
| API (backend)    | http://localhost:8080/clinica-gastro     |
| Banco PostgreSQL | localhost:5432 (db: `teste4`)            |

## 8. Comandos úteis

```bash
# Ver logs de todos os serviços
docker compose logs -f

# Ver logs de um serviço específico
docker compose logs -f backend

# Parar os serviços (mantém os dados do banco)
docker compose down

# Parar e APAGAR os dados do banco (volume)
docker compose down -v

# Reconstruir apenas o frontend
docker compose up --build frontend
```

## 9. Observações importantes

- **Primeira secretária:** se a rota `POST /usuario` exigir o cargo SECRETARIA,
  não será possível criar o primeiro usuário pela API. Insira a primeira
  secretária diretamente no banco (via SQL) ou por um script de seed.

- **Persistência:** os dados do PostgreSQL ficam no volume `db_data` e
  sobrevivem a `docker compose down`. Use `docker compose down -v` apenas se
  quiser zerar o banco.

- **CORS:** garanta que o backend permita requisições vindas de
  `http://localhost:3000` (configuração de CORS no Spring Security).

- **Produção:** em um servidor real, troque `NEXT_PUBLIC_API_URL` pelo domínio
  público da API e configure HTTPS (ex.: via proxy reverso Nginx ou Traefik).

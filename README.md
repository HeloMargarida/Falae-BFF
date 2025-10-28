# Falae BFF (Java / Spring Boot)

BFF para orquestrar e simplificar o acesso do Frontend ao backend .NET do projeto Falae.

## Como rodar no VS Code
1. Instale **Java 17+**, **Maven** e a extensão *Extension Pack for Java*.
2. Abra esta pasta no VS Code.
3. Ajuste a URL do backend no arquivo: `src/main/resources/application.properties` (`backend.base-url`).
4. No terminal, rode: `mvn spring-boot:run`.
5. O BFF iniciará em `http://localhost:8081`.

## Rotas do BFF

### Auth
- `POST /bff/auth/login`
- `POST /bff/auth/recuperar-senha`

### Usuários
- `GET /bff/usuarios`
- `GET /bff/usuarios/{id}`
- `POST /bff/usuarios`
- `PUT /bff/usuarios/{id}`
- `DELETE /bff/usuarios/{id}`

### Preferências
- `GET /bff/preferencias`
- `GET /bff/preferencias/{id}`
- `GET /bff/preferencias/usuario/{usuarioId}`
- `POST /bff/preferencias`
- `PUT /bff/preferencias/{id}`
- `DELETE /bff/preferencias/{id}`
- `DELETE /bff/preferencias/usuario/{usuarioId}`

### Locais de Encontro
- `GET /bff/locais`
- `GET /bff/locais/ativos`
- `GET /bff/locais/{id}`
- `POST /bff/locais`
- `PUT /bff/locais/{id}`
- `DELETE /bff/locais/{id}`
- `PATCH /bff/locais/{id}/ativar`
- `PATCH /bff/locais/{id}/desativar`
- `GET /bff/locais/{id}/encontros`

### Faturas
- `GET /bff/faturas`

### Database/Test
- `GET /bff/database/tables`
- `POST /bff/database/create-tables`

## Observações
- O BFF **não** acessa o MySQL. Quem acessa é o backend .NET.
- Para endpoints protegidos com JWT, o BFF **repassa** automaticamente o header `Authorization` para o backend.

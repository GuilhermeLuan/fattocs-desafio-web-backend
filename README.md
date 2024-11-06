<h1 align='center'> Fattocs Desafio Web API </h1>

<p align='center'>
Esta é uma API de lista de tarefa para o processo seletivo de estágio da Fattocs, foi projetado para armazenar informações sobre tarefas. Foi desenvolvido em Spring.
</p>

## 🔧 Ferramentas

- Java
- Spring Boot
- PostgreSQL
- Docker
- OpenAPI Swagger
- JUnit

## 📍 Endpoints

- `GET /v1/tasks`: Retorna a lista de todos as tarefas.
- `POST /v1/tasks`: Cria uma nova tarefa.
- `PUT /v1/tasks`: Atualiza as informações de uma tarefa específica.
- `DELETE /tarefa/{id}`: Exclui uma tarefa específica com base no ID.

## 📄 Modelos de Dados

- `Task`:
    - `id` (campo automático): O identificador único da tarefa.
    - `taskName` (string): Nome da tarefa.
    - `cost` (double): Custo da tarefa.
    - `dataLimit` (Date): Limite de data da tarefa.
    - `presentationOrder` (inteiro): Ordem de apresentação da tarefa.

## ⚙️ Uso da API

A API pode ser usada para:

- Listar todos as tarefas.
- Adicionar um novo tarefa.
- Atualizar informações de uma tarefa existente.
- Excluir uma tarefa específica.

## 💻 Como Executar o Projeto

Siga estas etapas para configurar e executar a API em seu ambiente:

1. Clone este repositório:

  ```shell
  git clone https://github.com/GuilhermeLuan/fattocs-desafio-web-backend.git
  ```
2. Navegue até o diretório do projeto:

  ```shell
  cd nomedoprojeto/
  ```
3. Iniciando:

  ```shell
  docker compose up
./mvnw clean install
./mvnw spring-boot:run
  ```

A API estará acessível em http://localhost:8080/.

A documentação estará acessível em http://localhost:8080/swagger-ui/index.html.

## 🌐 Exemplo de Solicitação

### Criar um Novo Livro

      Método: POST
      URL: http://localhost:8000/v1/tasks

### Corpo da Solicitação:

```json
{
  "taskName": "Finalizar back-end",
  "cost": 1500.0,
  "dataLimit": "2024-11-10"
}

```
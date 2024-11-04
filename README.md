# Microserviço de Notificação de Usuários

Desenvolvido uma API que permite o registro de usuários e a atualização de senhas utilizando Spring Boot. A API inclui segurança com JWT e um sistema de mensageria para comunicação com outro microserviço.

## Requisitos

Certifique-se de ter as seguintes ferramentas instaladas:

- Java Development Kit (JDK) 17 ou superior
- MySQL 8.0 ou superior
- Apache Kafka
- Docker e Docker Compose

## Estrutura do Projeto

- **api-user**: Código-fonte principal do microserviço de registro de usuários.
  - `src/main/java/com/uol/compass/api-user`: Código-fonte da aplicação.
  - `src/main/resources`: Recursos de configuração (application.properties).
- **notify**: Código-fonte principal do microserviço de notificação.
  - `src/main/java/com/uol/compass/notify`: Código-fonte da aplicação.
  - `src/main/resources`: Recursos de configuração (application.properties).
- **docker-compose.yml**: Configuração do Docker para orquestrar os serviços.
- **README.md**: Documentação do projeto.

## Funcionalidades

### Registro de Usuário

O microserviço `api-user` permite que novos usuários sejam registrados. Os usuários podem ser cadastrados com os seguintes detalhes:

- Nome
- Email
- Senha

Uma vez registrado, um evento é enviado ao Kafka para notificar o microserviço notify.

### Atualização de Senha

O microserviço `api-user` permite que usuários autenticados alterem a sua própria senha. Os usuários podem ser alterar a senha com os seguintes detalhes:

- Nome
- Senha antiga
- Senha nova

Uma vez atualizado, um evento é enviado ao Kafka para notificar o microserviço notify.

### Notificação de Usuário

O microserviço `notify` escuta os eventos do Kafka e envia notificações ao console quando um novo usuário é registrado ou tem sua senha atualizada. A notificação inclui detalhes como: nome do usuário e o tipo de operação.

## Endpoints

### Registro de Usuário

endpoint para registrar um usuário no banco

- **POST** `/api/users/register`

  **Corpo da requisição:**
  ```json
  {
    "username": "string",
    "email": "string",
    "password": "string",
    "cep": "string"
  }

endpoint para atualizar a senha do usuário

- **PUT** `/api/users/update-password`

  **Corpo da requisição:**
  ```json
  {
    "username": "string",
    "oldPassword": "string",
    "newPassword": "string"
  }

endpoint para gerar o Token para autenticação

- **POST** `/api/auth`

  **Corpo da requisição:**
  ```json
  {
    "email": "string",
    "password": "string"
  }


### Contato
* Nome: Gustavo Martins Gomes
* Email: gustavo.gomes.pb@compasso.com.br
* GitHub: github.com/gumartx
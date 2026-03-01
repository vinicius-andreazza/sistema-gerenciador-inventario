# 📦 Sistema Gerenciador de Inventário

Sistema web para **gerenciamento de estoque e inventário**, permitindo o controle de produtos, quantidades, categorias, usuários e movimentações.

Este projeto foi desenvolvido com foco em organização, controle e visualização eficiente do estoque, facilitando a gestão de itens e evitando problemas como falta de produtos ou excesso armazenado.

---

## 🚀 Funcionalidades

- 📋 Cadastro de produtos
- 📦 Controle de quantidade em estoque
- ⚠️ Alerta de estoque mínimo
- 📊 Cálculo do valor total do estoque
- 👤 Controle de usuários e permissões
- 🏷️ Categorias de produtos
- 📍 Controle de locais de armazenamento
- 📈 Dashboard com indicadores
- ✏️ Edição e exclusão de itens
- 🔎 Filtros e paginação de dados

---

## 🛠️ Tecnologias Utilizadas

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Spring Security

### Frontend
- HTML5
- CSS3
- JavaScript
- Thymeleaf

### Banco de Dados
- MySQL

---

## 🧱 Arquitetura do Projeto

O sistema segue o padrão de arquitetura em camadas:

- **Controller** → Recebe requisições HTTP  
- **Service** → Regras de negócio  
- **Repository** → Acesso ao banco de dados  
- **Model (Entity)** → Representação das tabelas  

Esse modelo garante organização, manutenção e escalabilidade do sistema.

---

## ⚙️ Pré-requisitos

Antes de executar o projeto, você precisa ter instalado:

- Java JDK 17+
- Maven
- MySQL Server
- Git

---

## 📥 Como executar o projeto

### 1. Clone o repositório

```bash
git clone https://github.com/vinicius-andreazza/sistema-gerenciador-inventario.git
```
Entre na pasta do projeto:
```bash
cd sistema-gerenciador-inventario
```
### 2. Configure o banco de dados

Crie um banco no MySQL:
```sql
CREATE DATABASE sgi;
```
Execute os scrpits DDL e DML da pasta resources no MySQL

Agora configure o arquivo:
```bash
src/main/resources/application.properties
```
Edite com suas credenciais:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/sgi
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
3. Executar a aplicação

No terminal, rode:
```bash
mvn spring-boot:run
```
ou, se estiver usando uma IDE como IntelliJ ou Eclipse:

Execute a classe principal do projeto (Spring Boot Application)

4. Acessar o sistema

Abra no navegador:
```url
http://localhost:8080
```
## 🔐 Acesso ao sistema

Entre com o usuario admin e senha admin

## 📂 Estrutura de Pastas
```
src
 ├── main
 │   ├── java/com/sig/sistema_gerenciador_inventario
 │   │   ├── controller
 │   │   ├── service
 │   │   ├── repository
 │   │   ├── model
 │   │   └── config
 │   └── resources
 │       ├── templates
 │       ├── static
 │       └── application.properties
 ```
## 📊 Indicadores do Sistema

O sistema calcula automaticamente:

- 💰 Valor total em estoque

- 📦 Quantidade total de itens

- ⚠️ Produtos com estoque abaixo do mínimo

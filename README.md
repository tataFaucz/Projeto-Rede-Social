# Projeto Rede Social

Projeto acadêmico desenvolvido para a disciplina de **Banco de Dados e Programação Orientada a Objetos**, com o objetivo de aplicar conceitos de modelagem, implementação e manipulação de um banco de dados relacional.

A aplicação representa o domínio de uma **rede social**, permitindo o gerenciamento de usuários, publicações, comentários, mensagens e hashtags, além das relações entre essas entidades.

O projeto utiliza **Java** para implementação da aplicação e **PostgreSQL** para armazenamento e gerenciamento dos dados.

---

## Índice

* [Descrição do Projeto](#descrição-do-projeto)
* [Domínio e Estrutura de Dados](#domínio-e-estrutura-de-dados)
* [Funcionalidades](#funcionalidades)
* [Tecnologias Utilizadas](#tecnologias-utilizadas)
* [Estrutura do Projeto](#estrutura-do-projeto)
* [Requisitos](#requisitos)
* [Configuração do Banco de Dados](#configuração-do-banco-de-dados)
* [Compilação e Execução](#compilação-e-execução)
* [Repositório](#repositório)
* [Autores](#autores)

---

## Descrição do Projeto

O **Projeto Rede Social** consiste na implementação de uma aplicação para gerenciamento de informações de uma rede social, desenvolvida como parte da **Fase 1 do projeto da disciplina de Banco de Dados**.

O sistema utiliza um banco de dados relacional para armazenar e relacionar informações referentes a usuários, fotos, comentários, mensagens e hashtags.

Além das operações de cadastro e manutenção das entidades, o sistema contempla operações relacionadas aos relacionamentos entre usuários e publicações, bem como consultas que envolvem múltiplas tabelas.

---

## Domínio e Estrutura de Dados

O banco de dados é estruturado a partir das seguintes entidades principais:

* **USUARIO** — representa os usuários da rede social;
* **FOTO** — representa as publicações de fotos;
* **COMENTARIO** — representa comentários realizados nas publicações;
* **MENSAGEM** — representa mensagens entre usuários;
* **HASHTAG** — representa hashtags associadas às publicações.

O modelo também possui relações associativas para representar:

* **SEGUIDOR** — relacionamento de seguimento entre usuários;
* **CURTIDA** — curtidas realizadas pelos usuários nas publicações;
* **COMPARTILHAMENTO** — compartilhamentos de publicações;
* **FOTO_HASHTAG** — associação entre fotos e hashtags.

---

## Funcionalidades

A aplicação contempla as operações previstas para a implementação do banco de dados.

### Operações sobre entidades

As entidades principais possuem operações de **CRUD**:

* Cadastro;
* Consulta;
* Alteração;
* Exclusão.

Essas operações são aplicadas às seguintes entidades:

* Usuários;
* Fotos;
* Comentários;
* Mensagens;
* Hashtags.

### Operações sobre relações

O sistema também contempla operações de negócio relacionadas às tabelas associativas:

* Seguir e deixar de seguir usuários;
* Curtir e remover curtida de uma publicação;
* Compartilhar uma publicação;
* Associar e remover hashtags de uma publicação.

### Consultas e relatórios

A aplicação possui consultas envolvendo mais de uma tabela, incluindo:

1. **Publicações por usuário**;
2. **Comentários das publicações**;
3. **Publicações associadas a uma hashtag**.

Essas consultas demonstram a utilização dos relacionamentos definidos no modelo relacional.

---

## Tecnologias Utilizadas

* **Java** — linguagem de programação;
* **Java Swing** — interface da aplicação;
* **PostgreSQL** — sistema gerenciador de banco de dados;
* **JDBC** — conexão entre a aplicação Java e o PostgreSQL;
* **PgAdmin4** — ferramenta utilizada para gerenciamento do banco de dados.

O acesso aos dados é organizado utilizando o padrão **DAO (Data Access Object)**.

---

## Estrutura do Projeto

```text
projeto rede social/
├── dados/        # Classes relacionadas às entidades e dados do sistema
├── dao/          # Classes responsáveis pelo acesso ao banco de dados
├── images/       # Imagens utilizadas pela aplicação
├── negocios/     # Regras de negócio e inicialização do sistema
├── ui/           # Interface gráfica da aplicação
└── README.md     # Documentação do projeto
```

### Principais responsabilidades

**dados/**
Contém as classes que representam as entidades utilizadas pela aplicação.

**dao/**
Contém as classes responsáveis pelas operações de persistência e acesso ao PostgreSQL.

**negocios/**
Contém a lógica de negócio e os fluxos de execução da aplicação.

**ui/**
Contém as classes responsáveis pela interface gráfica desenvolvida em Java Swing.

**images/**
Contém imagens utilizadas pela aplicação.

---

## Requisitos

Para executar o projeto, são necessários:

* Java 8 ou superior;
* PostgreSQL;
* Driver JDBC do PostgreSQL;
* IDE ou ambiente para compilação de projetos Java.

O banco de dados pode ser administrado utilizando o **PgAdmin4**.

---

## Configuração do Banco de Dados

Antes de executar a aplicação, é necessário possuir uma instância do PostgreSQL em execução.

Configure as informações de conexão utilizadas pela aplicação:

* **Host:** endereço do servidor PostgreSQL;
* **Porta:** porta utilizada pelo PostgreSQL;
* **Banco:** nome do banco de dados;
* **Usuário:** usuário do PostgreSQL;
* **Senha:** senha do usuário configurado.

Exemplo de configuração utilizada no ambiente de desenvolvimento:

```text
DB_HOST=::1
DB_PORT=5433
DB_NAME=omellety
DB_USER=postgres
DB_PASSWORD=sua-senha
```

> A senha não deve ser armazenada no repositório. Utilize a senha configurada no ambiente local do PostgreSQL.

---

## Compilação e Execução

### 1. Clonar o repositório

```powershell
git clone https://github.com/tataFaucz/Projeto-Rede-Social.git
```

### 2. Acessar o diretório do projeto

```powershell
cd Projeto-Rede-Social
```

### 3. Configurar as variáveis de ambiente

No PowerShell:

```powershell
$env:DB_HOST="::1"
$env:DB_PORT="5433"
$env:DB_NAME="omellety"
$env:DB_USER="postgres"
$env:DB_PASSWORD="sua-senha"
```

Substitua os valores conforme a configuração do PostgreSQL utilizado no ambiente.

### 4. Compilar o projeto

```powershell
$sources = Get-ChildItem -Recurse -Filter *.java | Select-Object -ExpandProperty FullName

javac -cp "lib\postgresql-42.7.7.jar" -d out $sources
```

### 5. Executar a aplicação

```powershell
java -cp "out;lib\postgresql-42.7.7.jar" negocios.Main
```

A aplicação será iniciada utilizando a configuração do banco de dados definida nas variáveis de ambiente.

---

## Repositório

O código-fonte, arquivos do projeto e demais materiais necessários para a execução estão disponíveis no repositório:

**GitHub:**
https://github.com/tataFaucz/Projeto-Rede-Social

O repositório faz parte da entrega da **Fase 1 do projeto de Banco de Dados**.

---

## Autores

**Thais Faucz Jasse**

Projeto desenvolvido para fins acadêmicos no curso de **Análise e Desenvolvimento de Sistemas — UDESC CCT**.

---

## Observação

Este projeto possui finalidade exclusivamente acadêmica e foi desenvolvido para aplicação prática dos conceitos de **bancos de dados relacionais, modelagem de dados, operações CRUD, relacionamentos entre entidades e consultas SQL**.

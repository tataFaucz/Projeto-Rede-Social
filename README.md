# Projeto Rede Social

Bem-vindo ao **Projeto Rede Social**!  
Este repositório contém uma aplicação modular, desenvolvida em Java, que simula as principais funcionalidades de uma rede social. O projeto utiliza Java Swing para interface gráfica e PostgreSQL (gerenciado via PgAdmin4) para persistência de dados.

---

## Índice

- [Descrição Geral](#descrição-geral)
- [Funcionalidades Principais](#funcionalidades-principais)
- [Estrutura de Pastas](#estrutura-de-pastas)
- [Requisitos](#requisitos)
- [Configuração do Banco de Dados](#configuração-do-banco-de-dados)
- [Como Executar](#como-executar)
- [Como Contribuir](#como-contribuir)
- [Autores e Créditos](#autores-e-créditos)
- [Licença](#licença)

---

## Descrição Geral

Este projeto simula uma rede social simples, com foco em boas práticas de organização de código, separação de responsabilidades e implementação do padrão DAO (Data Access Object).  
A aplicação é ideal para fins didáticos ou como base para projetos mais complexos.

- **Linguagem:** Java
- **Interface gráfica:** Java Swing
- **Banco de Dados:** PostgreSQL (acessado e gerenciado via PgAdmin4)

---

## Funcionalidades Principais

- Cadastro e autenticação de usuários
- Criação, edição e exclusão de postagens
- Comentários em postagens
- Upload e exibição de imagens
- Listagem de usuários, postagens e comentários
- Interface gráfica amigável
- Estrutura modular baseada em camadas

---

## Estrutura de Pastas

```
projeto rede social/
├── dados/        # Classes de modelo (Usuário, Postagem, Comentário, etc.)
├── dao/          # Padrão DAO, manipulação e persistência dos dados (PostgreSQL)
├── images/       # Armazenamento de imagens utilizadas na aplicação
├── negocios/     # Lógica de negócio e inicialização do sistema
├── ui/           # Interface gráfica do usuário (Java Swing)
└── README.md     # Este arquivo
```

### Explicação das Pastas

- **dados/**  
  Contém as representações das entidades do sistema, como Usuário, Postagem, Comentário, Foto, etc.

- **dao/**  
  Implementação do padrão DAO, separando a lógica de acesso ao banco de dados das regras de negócio.  
  Todas as operações com PostgreSQL são centralizadas aqui.

- **images/**  
  Armazena imagens de exemplo, avatares, fotos de postagens, etc.

- **negocios/**  
  Responsável pela lógica principal do sistema (regras de negócio), validações, fluxos e inicialização.

- **ui/**  
  Implementação da interface gráfica utilizando Java Swing, facilitando a interação do usuário com o sistema.

---

## Requisitos

- **Java 8** ou superior
- **PostgreSQL** (recomendado gerenciar via **PgAdmin4**)
- **Driver JDBC do PostgreSQL**
- **IDE Java** recomendada (Eclipse, IntelliJ IDEA ou NetBeans)

---

## Configuração do Banco de Dados

1. **Instale o PostgreSQL e o PgAdmin4.**
2. **Crie um banco de dados para o projeto** (exemplo: `rede_social`).
3. **Ajuste as configurações de conexão no projeto:**  
   No arquivo de configuração (ou diretamente nas classes DAO), informe:
   - URL do banco (ex: `jdbc:postgresql://localhost:5432/rede_social`)
   - Usuário e senha do banco

4. **Execute os scripts de criação de tabelas** (caso disponíveis na pasta do projeto ou fornecidos separadamente).

---

## Como Executar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/tataFaucz/Projeto-Rede-Social.git
   ```

2. **Abra o projeto em sua IDE Java de preferência.**

3. **Adicione o driver JDBC do PostgreSQL ao classpath do projeto.**

4. **Configure as credenciais do banco de dados conforme instruções acima.**

5. **Compile o projeto:**
   - Na IDE, utilize a opção de "Build Project"
   - Ou pelo terminal:
     ```bash
     javac -cp ".:path/to/postgresql.jar" negocios/Main.java
     ```

6. **Execute a aplicação:**
   - Pela IDE, execute a classe principal (geralmente `negocios.Main`)
   - Ou pelo terminal:
     ```bash
     java -cp ".:path/to/postgresql.jar" negocios.Main
     ```

---

## Como Contribuir

1. **Fork este repositório**
2. Crie uma branch para sua feature ou correção (`git checkout -b minha-feature`)
3. Faça commit das suas alterações (`git commit -am 'Adiciona nova feature'`)
4. Faça push para a branch criada (`git push origin minha-feature`)
5. Abra um Pull Request

**Sugestões, correções e melhorias são sempre bem-vindas!**

---

## Autores e Créditos

- Desenvolvedor principal: [@tataFaucz](https://github.com/tataFaucz)

---

## Licença

Este projeto é de cunho acadêmico.

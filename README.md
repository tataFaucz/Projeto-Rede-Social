# Projeto Rede Social

Este é um projeto inicial de uma rede social simples, desenvolvido em Java. O objetivo é permitir o cadastro de usuários, postagem de fotos e gerenciamento de seguidores.

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

```
Projeto-Rede-Social/
├── dados/
│   ├── Foto.java
│   └── Usuario.java
├── negocio/
│   ├── Main.java
│   └── Sistema.java
└── empresarial/
```

- `dados/`: Contém as classes de dados principais, como `Usuario` e `Foto`.
- `negocio/`: Contém a lógica de negócio, incluindo o sistema principal (`Sistema`) e a classe de execução (`Main`).
- `empresarial/`: Reservado para futuras expansões.

## Funcionalidades

- Cadastro de usuários com nome, e-mail e senha.
- Cadastro de fotos com descrição, caminho do arquivo e data de postagem.
- Usuários podem seguir e deixar de seguir outros usuários.
- Listagem de usuários e fotos cadastrados.

## Como Executar

1. Compile os arquivos `.java`:

   ```bash
   javac dados/*.java negocio/*.java
   ```

2. Execute a classe principal:

   ```bash
   java negocio.Main
   ```

## Possibilidades de Expansão

- Interface gráfica utilizando Java Swing ou JavaFX.
- Banco de dados para persistência de usuários e fotos.
- Sistema de curtidas e comentários nas fotos.
- Notificações e mensagens entre usuários.
- Autenticação com segurança reforçada (ex: hash de senhas).

## Classe Usuario

Arquivo: `dados/Usuario.java`

### Descrição

A classe `Usuario` representa um usuário da rede social. Ela armazena informações pessoais, lista de seguidores, usuários seguidos, publicações (fotos) e mensagens enviadas/recebidas. Também fornece métodos para seguir/deixar de seguir outros usuários, publicar fotos e enviar mensagens.

### Principais Atributos

- `nome`: Nome do usuário.
- `email`: Email do usuário.
- `senha`: Senha do usuário.
- `fotoPerfil`: Caminho ou referência para a foto de perfil.
- `seguindo`: Lista de usuários que este usuário está seguindo.
- `seguidores`: Lista de usuários que seguem este usuário.
- `publicacoes`: Lista de fotos/publicações do usuário.
- `mensagensEnviadas`: Mensagens enviadas pelo usuário.
- `mensagensRecebidas`: Mensagens recebidas pelo usuário.

### Principais Métodos

- **Construtor:**  
  `Usuario(String nome, String email, String senha, String fotoPerfil)`  
  Inicializa um novo usuário com os dados fornecidos.

- **Getters:**  
  Métodos para acessar os atributos privados.

- **seguir(Usuario outro):**  
  Segue outro usuário, se ainda não estiver seguindo e não for ele mesmo.

- **deixarDeSeguir(Usuario outro):**  
  Deixa de seguir outro usuário, se já estiver seguindo.

- **adicionarPublicacao(Foto foto):**  
  Adiciona uma nova foto/publicação à lista do usuário.

- **enviarMensagem(Usuario destinatario, String conteudo):**  
  Envia uma mensagem para outro usuário.

- **getMensagensEnviadas() / getMensagensRecebidas():**  
  Retorna as listas de mensagens enviadas e recebidas.

- **toString():**  
  Retorna uma representação textual do usuário (nome e email).

### Exemplo de Uso

```java
Usuario alice = new Usuario("Alice", "alice@email.com", "senha123", "alice.png");
Usuario bob = new Usuario("Bob", "bob@email.com", "senha456", "bob.png");

alice.seguir(bob);
alice.enviarMensagem(bob, "Olá, Bob!");
```

## Contribuição

Sinta-se à vontade para contribuir com o projeto. Para isso:

1. Faça um fork do repositório.
2. Crie uma branch para sua feature (`git checkout -b feature/NovaFeature`).
3. Faça commit das suas alterações (`git commit -m 'Adiciona nova feature'`).
4. Faça push para a branch (`git push origin feature/NovaFeature`).
5. Abra um Pull Request.

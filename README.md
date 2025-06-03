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

* `dados/`: Contém as classes de dados principais, como `Usuario` e `Foto`.
* `negocio/`: Contém a lógica de negócio, incluindo o sistema principal (`Sistema`) e a classe de execução (`Main`).
* `empresarial/`: Reservado para futuras expansões.

## Funcionalidades

* Cadastro de usuários com nome, e-mail e senha.
* Cadastro de fotos com descrição, caminho do arquivo e data de postagem.
* Usuários podem seguir e deixar de seguir outros usuários.
* Listagem de usuários e fotos cadastrados.

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

* Interface gráfica utilizando Java Swing ou JavaFX.
* Banco de dados para persistência de usuários e fotos.
* Sistema de curtidas e comentários nas fotos.
* Notificações e mensagens entre usuários.
* Autenticação com segurança reforçada (ex: hash de senhas).

## Contribuição

Sinta-se à vontade para contribuir com o projeto. Para isso:

1. Faça um fork do repositório.
2. Crie uma branch para sua feature (`git checkout -b feature/NovaFeature`).
3. Faça commit das suas alterações (`git commit -m 'Adiciona nova feature'`).
4. Faça push para a branch (`git push origin feature/NovaFeature`).
5. Abra um Pull Request.

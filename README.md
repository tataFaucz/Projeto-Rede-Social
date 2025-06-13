# Omeletty - Projeto Rede Social

Omeletty é uma rede social simples desenvolvida em Java com interface gráfica Swing, permitindo cadastro, login, publicação de fotos, comentários, curtidas, compartilhamentos, mensagens privadas, visualização e edição de perfis de usuários, além de explorar e seguir outros usuários.

---

## Estrutura do Projeto

```
imagem.bob.png
logo.png
dados/
    Comentario.java
    Foto.java
    Mensagem.java
    Usuario.java
negocios/
    Main.java
    Sistema.java
ui/
    CadastroPanel.java
    FeedPanel.java
    InicioPanel.java
    LoginPanel.java
    MainFrame.java
    PhotoPanel.java
    UserPanel.java
```

---

## Funcionalidades

- Cadastro e login de usuários
- Publicação de fotos com legenda
- Curtidas e compartilhamentos em fotos
- Comentários em fotos
- Mensagens privadas entre usuários
- Explorar outros usuários (pesquisa por nome, seguir/deixar de seguir)
- Visualização de perfil de outros usuários (nome, bio, seguidores, seguindo, fotos)
- Visualização e edição do próprio perfil (nome, biografia, foto de perfil)
- Interface gráfica amigável com logo personalizada

---

## Como Executar

**Pré-requisitos:**
- Java 8 ou superior instalado

**Compilação:**
Compile todos os arquivos `.java`:
```sh
javac dados/*.java negocios/*.java ui/*.java
```

**Execução:**
Execute a classe principal:
```sh
java negocios.Main
```

---

## Principais Arquivos

- `negocios/Main.java`: Inicializa o sistema e a interface gráfica.
- `negocios/Sistema.java`: Lógica principal do sistema (cadastro, login, publicações, etc).
- `dados/Usuario.java`: Representa um usuário da rede social.
- `dados/Foto.java`: Representa uma foto/publicação.
- `ui/MainFrame.java`: Janela principal da interface gráfica.
- `ui/LoginPanel.java`, `ui/CadastroPanel.java`, `ui/FeedPanel.java`: Telas da interface.

---

## Observações

- Imagens de exemplo e a logo devem estar no mesmo diretório do projeto (ex: `imagem.bob.png`, `logo.png`).
- O projeto é apenas para fins didáticos e pode ser expandido com novas funcionalidades.
- Desenvolvido para a disciplina de Programação Orientada a Objetos.

---

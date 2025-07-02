<h1 align="center">🍳 Omeletty - Sua Rede Social de Omelete! 🥚</h1>

<p align="center">
  <b>Uma rede social divertida e didática feita em Java Swing.<br>
  Compartilhe fotos, faça amigos, e curta muito!</b>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/Swing-GUI-blue"/>
  <img src="https://img.shields.io/badge/Projeto-Portfólio-success"/>
  <img src="https://img.shields.io/badge/PRs-Bem%20Vindos-green"/>
</p>

---

```
      🍳
   /-------\
  |  OMELETTY |
   \-------/
    (  •_•)
   <|     |>
    /     \
```

---

## 🥚 Sobre o Projeto

Omeletty é uma rede social simples e divertida, desenvolvida em Java com interface gráfica Swing. Permite que usuários criem contas, publiquem fotos, interajam com amigos, curtam, comentem, troquem mensagens e muito mais. Tudo isso com uma pegada didática e descontraída para mostrar suas skills de POO!

---

## 🗂️ Estrutura do Projeto

```
dados/
    Comentario.java
    Foto.java
    Mensagem.java
    Usuario.java
images/
    imagem.bob.png
negocios/
    logo.png
    Main.java
    perfil.tata.JPG
    Sistema.java
    uml.png
ui/
    CadastroPanel.java
    ExplorePanel.java
    FeedPanel.java
    InicioPanel.java
    LoginPanel.java
    MainFrame.java
    PhotoPanel.java
    UserPanel.java
```

---

## ✨ Funcionalidades

- Cadastro e login de usuários
- Publicação de fotos com legenda
- Curtidas, comentários e compartilhamentos
- Mensagens privadas entre usuários
- Explorar e seguir/desseguir outros usuários
- Visualizar e editar perfil (nome, bio, foto)
- Interface gráfica amigável e personalizada

---

## 🚀 Como Executar

**Pré-requisitos:**  
- Java 8 ou superior instalado

**Compilação:**  
No terminal, a partir da raiz do projeto:
```sh
javac dados/*.java negocios/*.java ui/*.java
```

**Execução:**  
```sh
java negocios.Main
```

---

## 🔍 Principais Arquivos

- `negocios/Main.java`: Inicializa o sistema e a interface gráfica
- `negocios/Sistema.java`: Regras de negócio e lógica principal
- `dados/Usuario.java`: Modela o usuário da rede social
- `dados/Foto.java`: Modela fotos/publicações
- `ui/MainFrame.java`: Janela principal da interface
- Telas de interface: `LoginPanel`, `CadastroPanel`, `FeedPanel`, etc.

---

## 🤔 Observações

- Imagens de exemplo e logo devem estar na raiz do projeto (`imagem.bob.png`, `logo.png`)
- Projeto para fins didáticos e portfólio, pode ser expandido com novas features!
- Desenvolvido originalmente para a disciplina de Programação Orientada a Objetos
- Mascote oficial: Omeletty, o Omelete mais social da internet! 🍳

---

## 💡 Próximos Passos / Ideias

- Upload real de imagens (atualmente é simulado)
- Melhorias no sistema de mensagens e notificações
- Novos temas para interface
- Deploy em Web/Cloud (ex: JavaFX com Spring Boot)

---

## 👩‍💻 Autor

Feito com carinho por [Tatá Faucz](https://github.com/tataFaucz)  
Siga, contribua ou me chame pra um café! ☕🥚

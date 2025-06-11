# Projeto Rede Social

Uma rede social simples desenvolvida em Java com interface gráfica Swing, permitindo cadastro, login, publicação de fotos, comentários, curtidas, compartilhamentos e mensagens privadas entre usuários.

## Estrutura do Projeto

```
imagem.bob.png
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
    ExplorePanel.java
    FeedPanel.java
    LoginPanel.java
    MainFrame.java
    PhotoPanel.java
    UserPanel.java
```

## Funcionalidades

- Cadastro e login de usuários
- Publicação de fotos com legenda
- Curtidas e compartilhamentos em fotos
- Comentários em fotos
- Mensagens privadas entre usuários
- Explorar outros usuários
- Visualização de perfil e publicações

## Como Executar

1. **Pré-requisitos:**  
   - Java 8 ou superior instalado

2. **Compilação:**  
   Compile todos os arquivos `.java`:
   ```sh
   javac dados/*.java negocios/*.java ui/*.java
   ```

3. **Execução:**  
   Execute a classe principal:
   ```sh
   java negocios.Main
   ```

## Principais Arquivos

- [`negocios/Main.java`](negocios/Main.java): Inicializa o sistema e a interface gráfica.
- [`negocios/Sistema.java`](negocios/Sistema.java): Lógica principal do sistema (cadastro, login, publicações, etc).
- [`dados/Usuario.java`](dados/Usuario.java): Representa um usuário da rede social.
- [`dados/Foto.java`](dados/Foto.java): Representa uma foto/publicação.
- [`ui/MainFrame.java`](ui/MainFrame.java): Janela principal da interface gráfica.
- [`ui/LoginPanel.java`](ui/LoginPanel.java), [`ui/CadastroPanel.java`](ui/CadastroPanel.java), [`ui/FeedPanel.java`](ui/FeedPanel.java): Telas da interface.

## Observações

- Imagens de exemplo devem estar no mesmo diretório do projeto (ex: `imagem.bob.png`).
- O projeto é apenas para fins didáticos e pode ser expandido com novas funcionalidades.

---

Desenvolvido para disciplina de Programação Orientada a Objetos.

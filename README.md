# Projeto-Rede-Social
Este é um projeto inicial de uma rede social simples, desenvolvido em Java. O objetivo é permitir o cadastro de usuários, postagem de fotos e gerenciamento de seguidores.

Estrutura do Projeto
dados/
    Foto.java
    Usuario.java
empresarial/
negocio/
    Main.java
    Sistema.java
dados/: Contém as classes de dados principais, como dados.Usuario e dados.Foto.
negocio/: Contém a lógica de negócio, incluindo o sistema principal (negocio.Sistema) e a classe de execução (negocio.Main).
empresarial/: (Reservado para futuras expansões).
Funcionalidades
Cadastro de usuários com nome, e-mail e senha.
Cadastro de fotos com descrição, caminho do arquivo e data de postagem.
Usuários podem seguir e deixar de seguir outros usuários.
Listagem de usuários e fotos cadastrados.
Como Executar

Compile os arquivos .java:

javac dados/*.java negocio/*.java

Execute a classe principal:

java negocio.Main

Próximos Passos
Implementar autenticação de usuários.
Permitir comentários e curtidas em fotos.
Criar interface gráfica ou API para interação.

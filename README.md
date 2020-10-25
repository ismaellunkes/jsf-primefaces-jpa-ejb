# Processo seletivo - Programador 1 JAVA

Bem vindo, candidato. 

Estamos felizes que você esteja participando do processo seletivo para a vaga de Programador 1 (JAVA) do Instituto SENAI de Tecnologia em Soluções digitais.

A prova utilizará das seguintes tecnologias: 
- Java para Web, versão 8 ou superior
- Banco de dados relacional
- GIT

Fica à sua escolha quais frameworks e servidor serão utilizados, desde que seja uma aplicação web em Java. 

O banco de dados relacional também fica a escolha do candidato. 

Na etapa da entrevista deverá ser apresentado a aplicação em funcionamento.

## Instruções para a execução da prova

A prova deve ser em JAVA Web. Você pode escolher a tecnologia que achar conveniente (JAVA EE, Spring, JSP...).

O Banco utilizado na prova deve ser relacional (Postgres, MySQL, Oracle...).

Esse repositório possui apenas esse Readme com as instruções da prova. No entanto, **todo desenvolvimento deve ser commitado nesse repositório** até a data citada no email, enviado por nossa equipe.

Commite nesse repositório o script utilizado na criação do banco de dados (se aplicável).

Por fim, altere esse arquivo com as instruções de como poderemos testar o seu código (quais libs usar, qual servidor, etc) abaixo.

## Instruções (do candidato) para o build do projeto

Utilizar a versão do JDK mais recente.

## 1. Download do Servidor de aplicação 

Fazer o download do servidor de aplicação (Wildfly) pré-configurado em [aqui][wildfly-link] e extrair em um local apropriado.

## 2. Clone do repositório do projeto

Clonar esse repositório dentro da pasta do workspace utilizado no Eclipse.

## 3. Arquivo para incialização do banco de dados no Docker

Instalar o docker: https://www.docker.com/get-started > Docker Desktop

O banco de dados é criado via docker e o fonte está disponível em https://drive.google.com/file/d/1bcSv6HSbP0MPiGgdYmLKM-zWIARn5Mvw/view?usp=sharing

Via terminal na pasta extraida e executar o banco pelo docker: `docker-compose up -d`

OBS: Se atentar que não pode haver nenhum servidor postgres rodando na máquina, se tiver, necessário fechar todos os serviços, assim como
qualquer outro serviço que esteja utilizando a porta 5432. Para verificar se o docker subiu corretamente, realizar a conexão com o banco:
- host: localhost
- port: 5432
- database: prova02db
- user: prova02user
- password: 123456

## 4. Execute o projeto

Após iniciado o servidor Wildfly execute o projeto.

- No navegador digitar http://localhost:8080/provaist/, irá mostrar a tela inicial com menu para acesso as funcionalidades.


[eclipse-windows]: https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/2020-03/R/eclipse-jee-2020-03-R-incubation-win32-x86_64.zip

[eclipse-linux]: https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/2020-03/R/eclipse-jee-2020-03-R-incubation-linux-gtk-x86_64.tar.gz

[eclipse-mac]: https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/2020-03/R/eclipse-jee-2020-03-R-incubation-macosx-cocoa-x86_64.dmg

[wildfly-link]: https://drive.google.com/file/d/1y2vgAYQw6ZPs3qrBU926ZZBezExarFU7/view?usp=sharing

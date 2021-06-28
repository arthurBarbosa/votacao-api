# votacao-api

Api criada para realização de voto de uma pauta de uma determinada assembléia.

# Acesso ao swagger-api da aplicação hospedado no Heroku

https://app-abcode-votation.herokuapp.com/swagger-ui.html

# Obs: Aplicação irá demorar alguns segundos para subir enquanto realiza o build no Heroku

## 🚀 Rodando a aplicação

Com o Docker instalado rode o comando: docker run -p 5432:5432 --name votacao -e POSTGRES_USER=votacao -e POSTGRES_PASSWORD=votacao -e POSTGRES_DB=votacao -d postgres:10.5-alpine

Configure o acesso ao banco de dados em seu gerenciador de prefrência e execute o comando sql que está no arquivo create.sql na pasta principal do projeto.

## 🚀 Acessando Documentação da aplicação através do swagger
http://localhost:8080/swagger-ui.html



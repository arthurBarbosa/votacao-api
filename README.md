# votacao-api

Api criada para realização de voto de uma pauta de uma determinada assembléia.
Esta api possui as funcionalidades de criar uma pauta, criar uma sessão para voto
contagem de votos após uma sessão fechada, funcionalidade de finalizar uma sessão.

# Tecnologia usadas
Java, Springboot, JPA para persistêcia de dados no banco de dados, Bean Validation 
para validação do objetos, uso do padrão data transfer object para trafego de objetos
na rede, banco de dados h2 para desenvolvimento, em produção esta aplicação está usando
Postgresql. A primeira versão desta aplicação esta hospedada no heroku usando o plano gratuito.

# Acesso ao swagger-api da aplicação hospedado no Heroku

https://app-abcode-votation.herokuapp.com/swagger-ui.html

# Obs: Aplicação irá demorar alguns segundos para subir enquanto realiza o build no Heroku

## 🚀 Rodando a aplicação

Faça o clone da aplicação em seu computador, realize o importe para a IDE de programação de seu gosto.
Após o importe do projeto mude a configuração do perfil do projeto para utilizar a configuração de test
Mude a configuração spring.active.profiles=prod para spring.active.profiles=test para rodar a aplicação
sem a necessidade de instalação de um banco de dados em sua maquina. Você também pode rodar o comando 
docker abaixo para a execução de um container de banco de dados.

Com o Docker instalado rode o comando: docker run -p 5432:5432 --name votacao -e POSTGRES_USER=votacao -e POSTGRES_PASSWORD=votacao -e POSTGRES_DB=votacao -d postgres:10.5-alpine

Configure o acesso ao banco de dados em seu gerenciador de prefrência e execute o comando sql que está no arquivo create.sql na pasta principal do projeto.

## 🚀 Endpoints disponilizados a api
 http://localhost:8080/schedules <br/>
 http://localhost:8080/schedules/{id}
 http://localhost:8080/schedules/all
 http://localhost:8080/associates/register
 http://localhost:8080/associates/{id}
 http://localhost:8080/associates/all
 http://localhost:8080/votations/1/count-result
 http://localhost:8080/sessions
 http://localhost:8080/sessions/{id}
 http://localhost:8080/sessions/{id}
 http://localhost:8080/votations/session/{id}?vote=true&associateId={id}
  
## 🚀 Acessando Documentação da aplicação através do swagger
http://localhost:8080/swagger-ui.html




[![codecov](https://codecov.io/gh/hackathon-da-diversidade/agencia-compromisso/branch/master/graph/badge.svg)](https://codecov.io/gh/hackathon-da-diversidade/agencia-compromisso)
[![hackathon-da-diversidade](https://circleci.com/gh/hackathon-da-diversidade/agencia-compromisso.svg?style=shield)](https://circleci.com/gh/hackathon-da-diversidade/agencia-compromisso)


# Agência Compromisso

Em parceria com a Lojas Renner, o [CEA](http://www.ceavilapinto.org/) (Centro de Educação Ambiental da Vila Pinto) começou a desenvolver um projeto para encontrar modelos de prova para suas criações dentro do bairro Bom Jesus. Para quem não é familiar com este termo, modelo de prova é a pessoa que experimenta peças-piloto antes da produção em larga escala. 

Para facilitar a localização de modelos de prova dentro da própria comunidade e, no caminho, fortalecer a economia local, nasceu a Agência Compromisso. Saiba mais acessando a página do [Facebook](https://www.facebook.com/agenciacompromisso/), [Instagram](https://www.instagram.com/agenciacompromisso/) e [Youtube](https://www.youtube.com/watch?v=3wyNRXZRt8c).

## Projeto

Para auxiliar na captação dos dados e medidas das modelos da comunidade e suas respectivas, construímos um primeiro MVP contendo um banco de dados associado ao Google Forms.
 
Durante o Hackathon da Diversidade, evento que aconteceu no escritório da Thoughtworks em Porto Alegre, começamos a trabalhar em nosso segundo MVP: uma interface customizada para facilitar ainda mais o processo cadastro e busca das modelos.

Após o evento, algumas pessoas seguiram trabalhando na iniciativa que tem como objetivo transformar o projeto em uma prancheta digital que pode ser acessada offline, com novas funcionalidades, como filtros, edição, busca e melhor usabilidade.


## Tecnologias Utilizadas

**Linguagem:** Java  
**Framework:** Spring Boot  
**Database:** MongoDB  
**Hosting:** Heroku  
**CI/CD:** CircleCI

*Durante o Hackathon da Diversidade o projeto foi iniciado com backend em Python + Flask. Após o evento as pessoas que seguiram trabalhando na iniciativa decidiram reescrever o backend usando Java + Spring boot.*

## Pré-requisitos

Para rodar a aplicação você precisa:

* [JDK 14](https://www.oracle.com/java/technologies/javase-jdk14-downloads.html)
* [Gradle](https://gradle.org/)

## Rodar aplicação

```
./gradlew bootRun
```

## Rodar testes

```
./gradlew test
```

## Conexão ao database

Para rodar a aplicação local certifique-se de configurar a conexão com algum MongoDB. Por padrão a aplicação irá se conectar ao mongo local na porta 27017. Você pode exportar as váriaveis de ambiente **MONGODB_URI** e **MONGODB_DATABASE** se desejar utilizar algum banco externo. As configurações se encontram no arquivo *application.properties*.

## CI/CD

O projeto conta com uma pipeline configurada no CircleCI que você pode acessar atráves desse [link](https://circleci.com/gh/hackathon-da-diversidade/agencia-compromisso). 
A cada novo *commit* na *master* a pipeline roda automáticamente, buildando e rodando todos os testes da aplicação. Caso esse processo seja um sucesso é feito o deploy no ambiente de [staging](https://agencia-compromisso-api-stg.herokuapp.com/). 
O deploy para [produção](https://agencia-compromisso-api.herokuapp.com/) é um passo manual e pode ser facilmente execultado dentro do CircleCI. 

## Ambientes

* [Staging](https://agencia-compromisso-api-stg.herokuapp.com/)
* [Produção](https://agencia-compromisso-api.herokuapp.com/)


## Contribuindo

Qualquer ajuda é bem vinda e adorariamos receber sua contribuição. Acesse o nosso [trello](https://trello.com/b/YL5SbWzZ/ag%C3%AAncia-compromisso) para entender o estágio atual do projeto, lá você encontra tudo que estamos trabalhando e o que queremos iniciar.
Procure também as pessoas envolvidas no projeto atualmente em caso de dúvidas.


## Acessos 

1. Faça download do Buttercup: https://buttercup.pw/
2. Clone o projeto
3. O arquivo que contém os acessos se chama "agencia_compromisso.bcup"
Para abrir o arquivo no programa você precisará da senha mestre. Pedir ela a alguém que esteja envolvido no projeto :)
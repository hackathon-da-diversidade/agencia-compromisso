
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

* [JDK 13](https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html)
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

## Links

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.
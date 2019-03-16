# Icaro - Chat

Software desenvolvido para exibir integração entre Spring boot + JSF (Primefaces) e websockets. Implementação de uma sala de bate-papo.

## Iniciando


### PRé-requisitos

 - O software utiliza banco de dados Oracle Express.
 - Foi utilizado o Flyway como ferramenta de evolução do DB. a sintaxe utilizada é voltada para o Oracle, então caso troque o banco de dados, certifique-se de alterar também os scripts.  
 - Possui integração com o Sendgrid (para envio de emails). o Token de envio está dentro do arquivo application.properties, e está apontado para a minha conta no site.
   Caso deseje, você poderá criar uma conta, gerar um token e substituí-lo.
 - Caso vá executar utilizando o Oracle, crie antes o usuário abaixo:
 
 
```
User: CHAT
PAssword: chat
```
   

### Executando

Para executar o projeto, execute o arquivo run (de acordo com seu sistema operacional). Caso prefira, clone ou faça um fork deste repositório, e execute o software através do Maven.


### testando

Abra em seu browser o endereço abaixo

```
 http://localhost:8080
```

Insira um email e um CPF válidos.

Para enviar uma mensagem privada, clique no nome do destinatário e escreva sua mensagem na caixa de texto que se abrirá.


## Built With

* [SpringBoot](https://spring.io/projects/spring-boot) 
* [JSF](https://www.oracle.com/technetwork/java/javaee/javaserverfaces-139869.html) 
* [Primefaces](https://www.primefaces.org/)
* [JPA](http://hibernate.org/)  
* [Oracle Express](https://www.oracle.com/database/technologies/appdev/xe.html)
* [Sendgrid](https://sendgrid.com/ ) 

## Autor

* **Icaro Afonso** - Software livre


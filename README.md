# product-ms
Microserviço criado para gerenciamento de um catálogo de produtos

## Tecnologias

- H2 Database;
- Lombok
- Maven;
- Modell Mapper;
- Java;
- Spring Boot;
- Spring Data JPA;

## Resumo
API projetada para realizar o gerencimaneto de produtos através de um microserviço de catálogo

### Funcionalidades

- Criação de um produto
- Atualização de um produto
- Busca de um produto por ID
- Lista de produtos
- Lista de produtos filtrados
- Deleção de um produto

## Acesso aos recursos da API
Para acessar os recursos da api é necessário realizar o envio das requisições para a url base *http://localhost:9999*, seguem abaixo os métodos de acesso
aos recursos da API.

| Verbo HTTP |   Resource path  |          Descrição          |
|:---------- |:---------------- |:--------------------------- |
|    POST    | /products        | Criação de um produto       |
|    PUT     | /products/{id}   | Atualização de um produto   |
|    GET     | /products        | Lista de produtos           |
|    GET     | /products{id}    | Busca de um produto por ID  |
|    GET     | /products/search | Lista de produtos filtrados |
|  DELETE    | /products/{id}   | Deleção de um produto       |

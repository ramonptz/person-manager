# Person Manager API - Java Spring

Uma Rest API em Java utilizando Spring Boot para gerenciamento de Pessoas e seus endereços. Nesta aplicação, é possível cadastrar indivíduos e associar múltiplos endereços a eles, onde cada pessoa pode sinalizar qual é o seu endereço principal. Além disso, ao preencher o CEP, a API verifica uma fonte externa para obter informações de rua, bairro e cidade automaticamente, desde que o CEP esteja correto.

## Recursos Principais

- Cadastro de Pessoas: Permite criar e manter o cadastro de pessoas com informações como nome, idade, telefone, entre outros atributos.

- Cadastro de Endereços: Permite adicionar e gerenciar os endereços associados a cada pessoa, marcando um deles como o endereço principal.

- Consulta de CEP: Ao informar um CEP válido, a API realiza uma consulta a uma fonte externa para obter informações adicionais de rua, bairro e cidade, facilitando o preenchimento do endereço.

## Tecnologias Utilizadas

- Java
- Spring Boot
- H2 Database (Banco de dados em memória)
- API Externa de Consulta de CEP (Integração para obtenção de informações de endereço)

## Pré-requisitos

- Java JDK 11
- Maven

## Instruções de Instalação

1. Clone o repositório do projeto:
   ```bash
   git clone https://github.com/ramonptz/person-manager.git
   cd person-manager

2. Compile o projeto utilizando o Maven:
     mvn clean package

3. Execute a aplicação:
   java -jar target/person-manager.jar

A aplicação estará disponível em http://localhost:8080.

## Documentação da API

Para consultar os endpoints disponíveis e seus detalhes, acesse a documentação interativa da API em http://localhost:8080/swagger-ui.html.

## Configurações Adicionais

- Caso necessário, é possível modificar as configurações do banco de dados H2 no arquivo `application.properties`.


## Contribuindo

Contribuições são bem-vindas! Se você deseja contribuir com melhorias, correções de bugs ou novos recursos, sinta-se à vontade para abrir um pull request.

## Contato

Se tiver alguma dúvida ou precisar de suporte, você pode entrar em contato com a equipe de desenvolvimento através do email: ramon.nvanderlei@gmail.com

#  Impostos API

Bem-vindo à **Impostos API**! Este projeto é uma aplicação Spring Boot que fornece uma API para gerenciar impostos. Abaixo, você encontrará informações sobre a estrutura do projeto, como rodar a aplicação, configurar o banco de dados local e executar os testes com relatórios de cobertura de código usando o JaCoCo.

## 🗂️ Estrutura do Projeto

O projeto está organizado nos seguintes pacotes:

- **`br.com.zup.Impostos`**: Contém a classe principal da aplicação.
- **`br.com.zup.Impostos.controllers`**: Controladores REST para expor os endpoints da API.
- **`br.com.zup.Impostos.exceptions`**: Contem as classes de exceções e GlobalExceptionHandler.
- **`br.com.zup.Impostos.infra`**: Configurações gerais sobre infraestrutura da API.
- **`br.com.zup.Impostos.services`**: Contém a lógica de negócios da aplicação.
- **`br.com.zup.Impostos.repositories`**: Repositórios para acesso ao banco de dados.
- **`br.com.zup.Impostos.models`**: Entidades utilizadas na aplicação.
- **`br.com.zup.Impostos.dtos`**: DTOs utilizados na aplicação.
- **`br.com.zup.Impostos.utils`**: utilitarios utilizados na aplicação.
- **`br.com.zup.Impostos.enums`**: enums utilizados na aplicação.

## 🚀 Como Rodar o Projeto

Siga os passos abaixo para rodar a aplicação localmente:

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/JorgeFVSz/Impostos.git
   ```

2. **Configure o banco de dados local**:
    - Certifique-se de ter um banco de dados PostgreSQL rodando.
    - Atualize a conexão do seu banco local de desenvolvimento ou
    - Atualize o arquivo `application.yml` com as credenciais do banco de dados local:
      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5433/crud_impostos
      spring.datasource.username=postgres
      spring.datasource.password=postgres
      ```

3. **Compile e rode a aplicação**:
   ```bash
   mvn spring-boot:run
   ```

4. **Acesso à API**:
    - A aplicação estará disponível em: [http://localhost:8080](http://localhost:8080)

5. **Acesso á documentação Swagger**:
    - A documentação swagger estará disponível em: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
    - Registrar um usuário.
    - Fazer login com o usuário registrado na base e copiar o JWT retornado.
    - Autenticar pelo swagger usando o JWT no botão Authorize e colar o valor do JWT.

## 🧾 Regras de Negócio

### Criação de Impostos
- Ao criar um novo imposto, o nome do imposto deve corresponder a um dos valores já cadastrados no enum `TaxType`.
- O enum `TaxType` contém a lógica de cálculo para cada tipo de imposto. Isso garante que, ao criar um imposto, a lógica de cálculo específica já estará associada ao tipo de imposto selecionado.
- Caso o nome do imposto não corresponda a um dos valores do enum `TaxType`, será lançada uma exceção informando que o tipo de imposto é inválido.

Essa abordagem foi implementada para permitir que cada tipo de imposto tenha sua lógica de cálculo específica, centralizada no enum `TaxType`. Isso facilita a manutenção e a adição de novos tipos de impostos com lógicas de cálculo personalizadas no futuro.

## 🧪 Como Rodar os Testes e Gerar Relatórios de Cobertura

O projeto utiliza o **JaCoCo** para medir a cobertura de código. Siga os passos abaixo para rodar os testes e gerar os relatórios:

1. **Execute os testes**:
   ```bash
   mvn clean verify
   ```

2. **Acesse o relatório**:
    - O relatório HTML será gerado no diretório `target/site/jacoco-merged-test-coverage-report/index.html`.
    - Abra o arquivo no navegador para visualizar a cobertura de código.
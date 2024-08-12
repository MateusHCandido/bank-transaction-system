# sistema de transações bancárias

## Sobre a aplicação

O sistema simula um fluxo de transação bancária, desde a criação de um banco até o ponto da transação. Neste sistema, 
é possível criar um banco, cadastrar contas para o banco, e durante o registro da conta do cliente, será registrado também
seu endereço.
 Ele atua de maneira simplificada, onde o intuito é implementar uma aplicação baseada em uma arquitetura de micro 
serviços inteligados por transmissões de eventos, em que cada serviço é trabalho com os critérios da arquitetura limpa, 
princípios SOLID e seus padrões.


## Como funciona

Um cliente poderá efetuar a criação se sua conta, e efetuar operações como transferência e depósito.

## Regras da aplicação:

- O email da conta deve ser único
- O cpf ou cnpj cadastrado deve ser único.
- Não será possível transferir um valor maior do que o saldo da conta
- Uma conta cujo cliente possuir idade menor que 6 não será criada
- Um código do banco deve ser único
- Todas as transações devem ser registradas
- Uma conta que é bloqueada, não pode fazer nenhuma transação, mas o seu saldo se permanece
- Uma conta que é cancelada, só poderá ser cancelada se possuir saldo zerado
- A transferência será válida se o cliente possuir saldo em conta disponível


## Informação sobre os dados:
### tabela account

- account_type: (N) -> Conta de pessoa física(natural person), (L) -> Conta de pessoa jurídica(legal person)
- account_status (A) -> Conta ativa, (B) -> Conta bloqueada, (C) -> Conta cancelada

### tabela transaction

- transaction_status: (S) -> Transação concluída com sucesso, (F) -> Transação com falha

### tabela log

- type_log: (E) -> Erro, (S) -> Indica sucesso

## Funcionalidades
### Banco
- Cria banco
- Lista todos os bancos
- Lista bancos por nome
- Busca banco por código do banco


### Conta
- Cria conta
- Busca conta por código da conta
- Busca conta por cpf ou cnpj
- Busca conta por email
- Realiza transferência de saldo bancário para outra conta existente
- Deposita valor em conta
- Altera email

## Tecnologias utilizadas

### Linguagem
- Java - versão 11

### Frameworks e APIs de desenvolvimento
- Spring Boot
- Spring Cloud
- Spring Data
- Hibernate

### Frameworks de testes
- JUnit
- Mockito

### Bibliotecas auxiliares
- Lombok
- ModelMapper

### Banco de dados
- MySQL para armazenar dados em produção
- PostgreSQL para realização desenvolvimento e testes

### Gerenciamento da aplicação
- Azure Cloud

### Mensageria
- Apache Kafka

### Teste de requisições
- Postman

# payment-ms

Este projeto é um microserviço dedicado ao **processamento de pagamentos**, focando na segurança e na validação das transações. Ele foi projetado para ser um serviço secundário (BFF - Backend For Frontend ou para comunicação entre microsserviços) que recebe e valida requisições de pagamento para, por exemplo, assinaturas premium.

-----

## 💻 Tecnologias

  * **Linguagem**: Java 21
  * **Framework**: Spring Boot 3.5.7
  * **Segurança**: Spring Security (OAuth2 Resource Server)
  * **Integridade dos Dados**: Validação de Assinatura Digital RSA (SHA256withRSA)
  * **Validação**: Jakarta Bean Validation
  * **Outros**: Lombok para boilerplate, Spring Cloud Stream (dependência presente no `build.gradle` para futura integração com mensageria, como RabbitMQ).

-----

## ✨ Funcionalidades e Validações

O serviço implementa um fluxo de processamento de pagamento focado em segurança e regras de negócio.

### 🔑 Segurança e Integridade

1.  **Autenticação OAuth2 (Resource Server)**: Todos os endpoints são protegidos pelo Spring Security, exigindo um token JWT válido emitido pelo Keycloak, conforme configurado em `src/main/resources/application.yml`.
2.  **Validação de Assinatura (Payload Hash)**: O endpoint de criação de pagamento requer um cabeçalho `x-payload-hash`. Este hash é uma assinatura digital do payload da requisição, que é validada usando a chave pública RSA (`certificate-public.key`).

### 📋 Regras de Negócio Implementadas

O `ProcessPaymentCase` executa validações antes de simular o processamento:

  * **Validação de Valor (`AmountValidation`)**: Garante que o valor da transação seja maior que zero.
  * **Validação de Data (`DateTimeValidation`)**: Garante que a data do pagamento não esteja no futuro.

-----

## 🧭 Endpoints da API

| Rota | Método | Descrição |
| :--- | :--- | :--- |
| `/v1/payments/create` | `POST` | Processa um novo pagamento. Requer um corpo de requisição com detalhes do pagamento e o cabeçalho `x-payload-hash` para validação da assinatura digital. |

### Corpo da Requisição (`PaymentRequest`)

| Campo | Tipo | Descrição | Validações |
| :--- | :--- | :--- | :--- |
| `userName` | `String` | Nome do usuário. | `@NotBlank` |
| `userEmail` | `String` | Email do usuário. | `@NotBlank` |
| `transactionValue` | `BigDecimal` | Valor da transação. | `@NotNull` e deve ser `> 0`|
| `type` | `PaymentType` | Forma de pagamento (`CREDIT_CARD`, `DEBIT_CARD`, `PIX`). | `@NotNull` |
| `paymentDate` | `LocalDateTime` | Data/hora do pagamento. | `@NotNull` e não pode ser futura |

-----

## ⚙️ Configuração Local

### Pré-requisitos

  * **Java 21**
  * **Gradle** (usando o Wrapper incluso: `gradlew`)

### Execução

1.  **Porta Padrão**: A aplicação é configurada para rodar na porta `8083`.

2.  **Keycloak (OAuth2)**: A configuração para o Resource Server espera um Realm Keycloak acessível, configurado para o cliente `task-manager-dev`.

3.  **Executar a Aplicação**:

    ```bash
    ./gradlew bootRun
    ```

    A aplicação estará acessível em `http://localhost:8083`.

### Tratamento de Erros

O `RestExceptionHandler` centraliza o tratamento de exceções:

  * **`PaymentException`**: Retorna `400 Bad Request` (erros de regra de negócio, ex: valor inválido).
  * **`CertificateException`**: Retorna `401 Unauthorized` (falha na validação da assinatura do payload).
  * **`MethodArgumentNotValidException`**: Retorna `400 Bad Request` com a lista de erros de validação do DTO.
  * **`Exception` (Genérico)**: Retorna `500 Internal Server Error`.

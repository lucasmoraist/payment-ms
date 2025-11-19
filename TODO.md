1. Criar uma classe Pagamento com os seguintes atributos:
    - [x] nome do usuário
    - [x] email do usuário
    - [x] valor da transação
    - [x] status do pagamento (pendente, concluído, falhado)
    - [x] data da transação
    - [x] forma de pagamento (cartão de crédito, boleto, pix)
2. Implementar métodos na classe Pagamento para:
    - [ ] validar se a data da transação não passada data atual ou futura
    - [ ] validar valor da transação (deve ser maior que zero)
    - [ ] processar o pagamento (simular apenas lançando um log)
3. Implementar um endpoint para criar um novo pagamento, que deve:
    - [ ] receber os dados do pagamento via requisição HTTP POST
    - [ ] receber os dados em formato JSON
    - [ ] validar token gerado por OAuth2
    - [ ] receber o payload assinado com certificado RSA 3072 bits em base64
    - [ ] validar a assinatura do payload
    - [ ] chamar os métodos de validação da classe Pagamento
    - [ ] retornar uma resposta JSON com o status do pagamento e uma mensagem apropriada
4. Implementar tratamento de erros para:
    - [ ] dados inválidos (email, valor, data)
    - [ ] falha na validação do token OAuth2
    - [ ] falha na validação da assinatura do payload
    - [ ] erros inesperados durante o processamento do pagamento
5. Escrever testes unitários para:
    - [ ] validar email
    - [ ] validar data da transação
    - [ ] validar valor da transação
    - [ ] processar pagamento
    - [ ] criar novo pagamento via endpoint HTTP
6. Documentar a API criada, incluindo:
    - [ ] descrição dos endpoints
    - [ ] exemplos de requisições e respostas
    - [ ] códigos de status HTTP retornados
    
ps: Conforme você for avançando nas tarefas, marcar conclusões com [x].
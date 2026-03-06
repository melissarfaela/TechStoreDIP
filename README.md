<div align="center">
  
  # Refatoração para o Princípio da Inversão de Dependência (DIP)
    
#### Unidade Curricular: Arquitetura de Sistemas | Professor: Lucas Santos 

<br>

![BunnyComputerGIF](https://github.com/user-attachments/assets/313adaca-5758-403e-a375-652a745ab0f7)


</div>

--------

<br>


## | Sistema de Recuperação de Senha da TechStore 🔑

### Contextualização

A TechStore possui um serviço responsável por permitir que os usuários recuperem suas senhas.
Inicialmente, o sistema envia o link de recuperação apenas por e-mail, utilizando uma integração direta com um servidor SMTP.

O problema dessa abordagem é que a lógica de recuperação de senha está diretamente ligada ao serviço de e-mail, criando um forte acoplamento entre as classes.

Caso a empresa queira adicionar novos meios de envio, como SMS ou WhatsApp, o código existente precisará ser modificado.

<br>

### O Problema: Violação do DIP

No código inicial, a classe RecuperadorDeSenha depende diretamente da classe concreta ServicoEmail.

Isso causa alguns problemas:

**Acoplamento rígido**: A classe RecuperadorDeSenha está diretamente ligada à implementação ServicoEmail.
Se a empresa quiser adicionar outro tipo de envio, como SMS, será necessário modificar essa classe.

**Dificuldade de manutenção**: Sempre que um novo método de envio for adicionado, o código da regra de negócio precisará ser alterado.

**Dificuldade de testes**: Não é possível testar o RecuperadorDeSenha sem realmente tentar enviar um e-mail, pois a dependência é criada dentro da própria classe.

<br>



### O Princípio DIP

O Princípio da **Inversão de Dependência (Dependency Inversion Principle)** afirma:

````
“Módulos de alto nível não devem depender de módulos de baixo nível.
Ambos devem depender de abstrações.”
````

Isso significa que:

- A lógica principal do sistema deve depender de interfaces.

- As implementações concretas devem seguir esse contrato.

Assim, a lógica de recuperação de senha não precisa saber como a mensagem será enviada, apenas que existe um serviço responsável por enviar mensagens.

<br>

### Solução Aplicando DIP

Para resolver o problema, foi criada uma interface de comunicação chamada:
````
ServicoMensageria
````

Essa interface define o contrato:
````
enviar(String mensagem)
````

Qualquer classe que implemente essa interface poderá ser utilizada para enviar mensagens.

### Estrutura:

````
TechStoreDIP
└── src
    └── main
        └── java
            ├── service
            │   ├── RecuperarSenha.java
            │   └── ServicoEmail.java
            │
            ├── strategy
            │   └── ServicoMensageria.java
            │
            └── util
                └── Main.java
````

<br>

### Com o DIP Aplicado:
É possível utilizar diferentes formas de envio sem alterar o código da classe RecuperarSenha.

**Utilizando E-mail**

````
ServicoMensageria email = new ServicoEmail();
RecuperarSenha recuperarSenha = new RecuperarSenha(email);

recuperarSenha.recuperar("usuario@techstore.com");
````

**Utilizando SMS**
````

ServicoMensageria sms = new ServicoSMS();
RecuperarSenha recuperarSenha = new RecuperarSenha(sms);

recuperarSenha.recuperar("usuario@techstore.com");
````

<br>

## Explique o Mau Design (DIP)

O mau design ocorre porque a classe **RecuperadorDeSenha** depende diretamente da classe concreta **ServicoEmail**. Isso cria um forte acoplamento entre a lógica de negócio (recuperação de senha) e o mecanismo específico de envio de mensagens.

Essa dependência direta viola o **Princípio da Inversão de Dependência (DIP)**, que afirma que módulos de alto nível não devem depender de módulos de baixo nível, mas sim de abstrações.

Com essa estrutura, qualquer alteração no método de envio — como adicionar suporte para **SMS, WhatsApp ou notificações push** — exigiria modificações na própria classe **RecuperadorDeSenha**, aumentando o risco de erros e dificultando a manutenção do sistema.

Além disso, esse acoplamento dificulta a realização de testes, pois a classe sempre tentará utilizar o serviço real de e-mail, impossibilitando o uso de implementações simuladas (mocks) para testes automatizados.

<br>

### Benefícios da Aplicação do DIP

Aplicando o Princípio da Inversão de Dependência, o sistema passa a ter:

- Baixo acoplamento

- A classe de recuperação de senha não depende mais de implementações específicas.

- Maior flexibilidade

- Novos tipos de envio podem ser adicionados facilmente (WhatsApp, Push Notification, etc).

- Facilidade de testes

- Agora é possível criar implementações simuladas (mock) para testes.

- Melhor manutenção

- Alterações em serviços de envio não afetam a lógica principal do sistema.

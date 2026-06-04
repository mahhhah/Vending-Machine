# Vending Machine

Projeto Java para uma máquina de vending, organizado com princípios SOLID e padrões de desenho.

## Funcionalidades

- Registar produtos com código, nome, preço e stock.
- Comprar produtos por código.
- Pagamento por dinheiro ou cartão.
- Validação de produto inválido, falta de stock e saldo insuficiente.
- Testes unitários com JUnit.

## Design patterns usados

1. **Factory Method**
   - Classe: `ProductFactory`
   - Responsável por criar produtos sem espalhar a lógica de criação pelo código.

2. **Strategy**
   - Interface: `PaymentStrategy`
   - Implementações: `CashPayment` e `CardPayment`
   - Permite trocar a forma de pagamento sem alterar a lógica da máquina.

3. **Singleton**
   - Classe: `VendingMachine`
   - Método: `getInstance()`
   - Fornece uma instância partilhada da máquina quando a aplicação precisar.

## Princípios SOLID aplicados

- **Single Responsibility Principle:** cada classe tem uma responsabilidade clara: inventário, produto, pagamento ou máquina.
- **Open/Closed Principle:** novos métodos de pagamento podem ser adicionados criando outra implementação de `PaymentStrategy`.
- **Liskov Substitution Principle:** qualquer `PaymentStrategy` pode ser usada pela `VendingMachine`.
- **Interface Segregation Principle:** a interface de pagamento só contém o método necessário para pagar.
- **Dependency Inversion Principle:** a máquina depende da abstração `PaymentStrategy`, não de classes concretas.

## Executar testes

//```bash
mvn test
```

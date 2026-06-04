package com.vendingmachine.vending_machine.strategy;


/**
 * Implementação do PaymentStrategy para pagamentos em dinheiro
 */
public class CashPayment implements PaymentStrategy {
    private double balance;

    /**
     * Construtor do pagamento com dinheiro que 
     * inicializa o saldo a zero
     */
    public CashPayment() {
        this.balance = 0;
    }

    /**
     * Construtor do pagamento com dinheiro que
     * inicializa o saldo com o valor dado
     * 
     * @param initialAmount		valor inicial do saldo
     * @throws IllegalArgumentException se o valor não for positivo
     */
    public CashPayment(double initialAmount) {
        insertMoney(initialAmount);
    }

    /**
     * Insere dinheiro na máquina incrementando o saldo
     * com o valor dado
     * 
     * @param amount	valor dado
     * @throws IllegalArgumentException se o valor não for positivo
     */
    public void insertMoney(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor inserido tem de ser positivo");
        }
        balance += amount;
    }

    /**
     * Retorna o saldo disponivel
     * 
     * @return	saldo disponivel
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Processa o pagamento do valor a pagar com dinheiro
     * 
     * @param amount	valor a pagar
     * @return			true se o pagamento for bem sucedido
	 * 					false se o pagamento não for bem sucedido
	 * @throws IllegalArgumentException se o valor a pagar não for positivo
     */
    @Override
    public boolean pay(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor a pagar tem de ser positivo");
        }
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    /**
     * Devolve o saldo ainda disponivel e coloca-o a zero
     * 
     * @return	saldo ainda disponivel
     */
    public double refund() {
        double amount = balance;
        balance = 0;
        return amount;
    }
}

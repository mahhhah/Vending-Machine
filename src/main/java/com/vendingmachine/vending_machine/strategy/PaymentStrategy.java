package com.vendingmachine.vending_machine.strategy;

/**
 * Representa a interface de qualquer método de pagamento a ser 
 * utilizado pela máquina de vendas
 * Implementa o pattern Strategy, permitindo que a máquina utilize 
 * todos os métodos de pagamento que implementem o PaymentStrategy
 */
public interface PaymentStrategy {
	/**
	 * Processa o pagamento do valor a pagar
	 * 
	 * @param amount	valor a pagar
	 * @return			true se o pagamento for bem sucedido
	 * 					false se o pagamento não for bem sucedido
	 */
    boolean pay(double amount);
}

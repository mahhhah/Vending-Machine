package com.vendingmachine.vending_machine.exception;


/**
 * Exceção lançada quando o saldo ou o limite disponivel para gastar
 * é insuficiente para efectuar o pagamento
 */
public class InsufficientFundsException extends RuntimeException {
    
	/**
	 * Construtor da exceção com a mensagem dada
	 * 
	 * @param message	mensagem sobre o erro
	 */
	public InsufficientFundsException(String message) {
        super(message);
    }
}

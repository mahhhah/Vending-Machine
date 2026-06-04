package com.vendingmachine.vending_machine.exception;


/**
 * Exceção lançada quando o produto não é válido
 */
public class InvalidProductException extends RuntimeException {
	
	/**
	 * Construtor da exceção com a mensagem dada
	 * 
	 * @param message	mensagem sobre o erro
	 */
    public InvalidProductException(String message) {
        super(message);
    }
}

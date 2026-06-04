package com.vendingmachine.vending_machine.exception;


/**
 * Exceção lançada quando o produto está sem stock
 */
public class OutOfStockException extends RuntimeException {
	
	/**
	 * Construtor da exceção com a mensagem dada
	 * 
	 * @param message	mensagem sobre o erro
	 */
    public OutOfStockException(String message) {
        super(message);
    }
}

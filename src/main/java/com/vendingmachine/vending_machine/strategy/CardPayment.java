package com.vendingmachine.vending_machine.strategy;


/**
 * Implementação do PaymentStrategy para pagamentos com cartão
 */
public class CardPayment implements PaymentStrategy {
    private final String cardNumber;
    private double availableLimit;

    /**
     * Construtor do pagamento com cartão através do nº do cartão
     * e do limite do cartão dados
     * 
     * @param cardNumber		nº do cartão
     * @param availableLimit	limite disponivel
     * @throws IllegalArgumentException se o número do cartão for inválido
     * 									ou se o limite disponivel for negativo
     */
    public CardPayment(String cardNumber, double availableLimit) {
        if (cardNumber == null || cardNumber.isBlank()) {
            throw new IllegalArgumentException("Número de cartão inválido");
        }
        if (availableLimit < 0) {
            throw new IllegalArgumentException("O limite disponível não pode ser negativo");
        }
        this.cardNumber = cardNumber;
        this.availableLimit = availableLimit;
    }

    /**
     * Retorna o número do cartão mascarado, só mostrando os últimos 4 digitos
     * 
     * @return	número do cartão em formato "**** **** **** XXXX"
     */
    public String getMaskedCardNumber() {
        if (cardNumber.length() <= 4) {
            return "****";
        }
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }

    /**
     * Retorna o limite disponivel no cartão
     * 
     * @return	limite disponivel no cartão
     */
    public double getAvailableLimit() {
        return availableLimit;
    }

    /**
     * Processa o pagamento do valor a pagar com cartão
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
        if (availableLimit >= amount) {
            availableLimit -= amount;
            return true;
        }
        return false;
    }
}

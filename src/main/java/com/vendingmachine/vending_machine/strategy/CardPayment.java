package com.vendingmachine.vending_machine.strategy;

public class CardPayment implements PaymentStrategy {
    private final String cardNumber;
    private double availableLimit;

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

    public String getMaskedCardNumber() {
        if (cardNumber.length() <= 4) {
            return "****";
        }
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }

    public double getAvailableLimit() {
        return availableLimit;
    }

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

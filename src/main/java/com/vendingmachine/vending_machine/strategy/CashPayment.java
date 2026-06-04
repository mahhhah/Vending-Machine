package com.vendingmachine.vending_machine.strategy;

public class CashPayment implements PaymentStrategy {
    private double balance;

    public CashPayment() {
        this.balance = 0;
    }

    public CashPayment(double initialAmount) {
        insertMoney(initialAmount);
    }

    public void insertMoney(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor inserido tem de ser positivo");
        }
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }

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

    public double refund() {
        double amount = balance;
        balance = 0;
        return amount;
    }
}

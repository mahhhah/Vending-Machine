package com.vendingmachine.vending_machine.model;

import com.vendingmachine.vending_machine.exception.OutOfStockException;

public class Slot {
    private final Product product;
    private int quantity;

    public Slot(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("O produto é obrigatório");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser negativa");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean hasStock() {
        return quantity > 0;
    }

    public void addStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("A quantidade a adicionar tem de ser positiva");
        }
        quantity += amount;
    }

    public void dispenseOne() {
        if (!hasStock()) {
            throw new OutOfStockException("Produto sem stock: " + product.getCode());
        }
        quantity--;
    }
}

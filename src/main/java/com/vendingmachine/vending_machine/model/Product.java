package com.vendingmachine.vending_machine.model;

import java.util.Objects;

public final class Product {
    private final String code;
    private final String name;
    private final double price;

    public Product(String code, String name, double price) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("O código do produto é obrigatório");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("O preço tem de ser positivo");
        }
        this.code = code.trim().toUpperCase();
        this.name = name.trim();
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return code.equals(product.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return code + " - " + name + " (€" + price + ")";
    }
}

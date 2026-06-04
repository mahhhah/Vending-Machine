package com.vendingmachine.vending_machine.model;

import java.util.Objects;


/**
 * Representa um produto imutavel da máquina de vendas
 */
public final class Product {
    private final String code;
    private final String name;
    private final double price;

    
    /**
     * Construtor do produto com o código, nome e preço dados
     * 
     * @param code		código do produto
     * @param name		nome do produto
     * @param price		preço do produto
     * @throws IllegalArgumentException se o código ou o nome forem nulos ou vazios,
     * 									ou se o preço não for positivo
     */
    public Product(String code, String name, double price) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("O código do produto é obrigatório");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("O preço do produto tem de ser positivo");
        }
        this.code = code.trim().toUpperCase();
        this.name = name.trim();
        this.price = price;
    }

    /**
     * Retorna o código do produto
     * 
     * @return	código em maiúsculas
     */
    public String getCode() {
        return code;
    }

    /**
     * Retorna o nome do produto
     * 
     * @return	nome do produto
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna o preço do produto
     * 
     * @return	preço do produto
     */
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

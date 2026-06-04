package com.vendingmachine.vending_machine.model;

import com.vendingmachine.vending_machine.exception.OutOfStockException;


/**
 * Representa um slot da máquina de vendas
 */
public class Slot {
    private final Product product;
    private int quantity;

    /**
     * Construtor do slot com o produto e quantidade dados
     * 
     * @param product	produto associado slot
     * @param quantity	quantidade inicial em stock
     * @throws IllegalArgumentException se o produto for null,
     * 									ou se a quantidade for negativa
     */
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

    /**
     * Retorna o produto associado ao slot
     * 
     * @return	produto do slot
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Retorna a quantidade atual em stock
     * 
     * @return	quantidade em stock
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Verifica se existe stock disponivel no slot
     * 
     * @return	true se houver stock
     * 			false se não houver stock
     */
    public boolean hasStock() {
        return quantity > 0;
    }

    /**
     * Adiciona stock ao slot
     * 
     * @param amount	quantidade do produto a adionar ao stock
     * @throws	IllegalArgumentException se a quantidade a adicionar não for positiva
     */
    public void addStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("A quantidade a adicionar tem de ser positiva");
        }
        quantity += amount;
    }

    /**
     * Dispensa uma unidade do produto ao stock
     * 
     * @throws OutOfStockException se não existir stock disponivel
     */
    public void dispenseOne() {
        if (!hasStock()) {
            throw new OutOfStockException("Produto sem stock: " + product.getCode());
        }
        quantity--;
    }
}

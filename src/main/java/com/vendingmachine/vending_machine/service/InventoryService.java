package com.vendingmachine.vending_machine.service;

import com.vendingmachine.vending_machine.exception.InvalidProductException;
import com.vendingmachine.vending_machine.exception.OutOfStockException;
import com.vendingmachine.vending_machine.model.Product;
import com.vendingmachine.vending_machine.model.Slot;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Representa o serviço responsável pela gestão do inventário da 
 * máquina de vendas
 */
public class InventoryService {
    private final Map<String, Slot> slots = new LinkedHashMap<>();

    /**
     * Adiciona um produto ao inventário, se o produto já existir
     * incrementa o seu stock
     * 
     * @param product	produto a adicionar
     * @param quantity	quantidade a adicionar
     * @throws IllegalArgumentException se a quantidade não for positiva
     */
    public void addProduct(Product product, int quantity) {
        if(quantity <= 0) {
        	throw new IllegalArgumentException("A quantidade a adicionar tem de ser positiva");
        }
    	String code = normalizeCode(product.getCode());
        Slot existingSlot = slots.get(code);
        if (existingSlot == null) {
            slots.put(code, new Slot(product, quantity));
        } else {
            existingSlot.addStock(quantity);
        }
    }

    /**
     * Retorna o produto associado ao código dado
     * 
     * @param code	código do produto
     * @return		produto associado ao código dado
     * @throws InvalidProductException se o código do produto for inválido
     */
    public Product getProduct(String code) {
        return getSlot(code).getProduct();
    }

    /**
     * Retorna a quantidade do stock do produto 
     * associado ao código dado
     * 
     * @param code	código do produto
     * @return		quantidade do stock do produto
     * @throws InvalidProductException se o código do produto for inválido
     */
    public int getStock(String code) {
        return getSlot(code).getQuantity();
    }

    /**
     * Dispensa uma unidade do produto associado ao código 
     * 
     * @param code	código do produto
     * @throws InvalidProductException se o código do produto for inválido
     * @throws OutOfStockException se o produto associado ao código não tiver stock
     */
    public void dispenseProduct(String code) {
        getSlot(code).dispenseOne();
    }

    /**
     * Retorna uma coleção imutavel de todos os slots no inventário
     * 
     * @return	coleção de slots
     */
    public Collection<Slot> listProducts() {
        return Collections.unmodifiableCollection(slots.values());
    }

    /**
     * Apaga todos os produtos do inventário
     */
    public void clear() {
        slots.clear();
    }

    /**
     * Retorna o slot do produto associado ao código dado
     * 
     * @param code	código do produto
     * @return		slot do produto associado ao código
     * @throws InvalidProductException se o código do produto for inválido
     * @throws InvalidProductException se o produto for inválido
     */
    private Slot getSlot(String code) {
        Slot slot = slots.get(normalizeCode(code));
        if (slot == null) {
            throw new InvalidProductException("Produto inválido: " + code);
        }
        return slot;
    }

    /**
     * Retorna o código dado em maiúsculas
     * 
     * @param code	código
     * @return		código em maiúsculas
     * @throws InvalidProductException se o código do produto for inválido
     */
    private String normalizeCode(String code) {
        if (code == null || code.isBlank()) {
            throw new InvalidProductException("Código de produto inválido");
        }
        return code.trim().toUpperCase();
    }
}

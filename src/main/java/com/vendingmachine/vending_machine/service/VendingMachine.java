package com.vendingmachine.vending_machine.service;

import com.vendingmachine.vending_machine.exception.InsufficientFundsException;
import com.vendingmachine.vending_machine.exception.OutOfStockException;
import com.vendingmachine.vending_machine.model.Product;
import com.vendingmachine.vending_machine.strategy.PaymentStrategy;


/**
 * Representa a máquina de vendas
 * Implementa o pattern Singleton para garantir uma única instância da máquina
 */
public class VendingMachine {
    private static final VendingMachine INSTANCE = new VendingMachine(new InventoryService());

    private final InventoryService inventoryService;

    /**
     * Construtor da máquina de vendas
     * 
     * @param inventoryService	serviço de inventário
     * @throws IllegalArgumentException se o serviço de inventário for null
     */
    public VendingMachine(InventoryService inventoryService) {
        if (inventoryService == null) {
            throw new IllegalArgumentException("O serviço de inventário é obrigatório");
        }
        this.inventoryService = inventoryService;
    }

    /**
     * Retorna a instancia partilhada da maquina de vendas
     * 
     * @return	instancia única da maquina de vendas
     */
    public static VendingMachine getInstance() {
        return INSTANCE;
    }

    /**
     * Adiciona um produto de acordo com o produto e quantidade dados
     * 
     * @param product		produto a adicionar
     * @param quantity		quantidade a adicionar
     * @throws IllegalArgumentException se a quantidade não for positiva
     */
    public void addProduct(Product product, int quantity) {
        inventoryService.addProduct(product, quantity);
    }

    /**
     * Compra um produto da máquina usando o método de pagamento fornecido
     * 
     * @param code				código do produto a comprar
     * @param paymentStrategy	método de pagamento
     * @return					o produto comprado
     * @throws IllegalArgumentException se o método de pagamento for null
     * @throws IllegalArgumentException se o código do produto for inválido
     * @throws OutOfStockException se o produto não estiver em stock
     * @throws InsufficientFundsException se o saldo não é suficiente para comprar o produto
     */
    public Product buyProduct(String code, PaymentStrategy paymentStrategy) {
        if (paymentStrategy == null) {
            throw new IllegalArgumentException("Método de pagamento obrigatório");
        }

        Product product = inventoryService.getProduct(code);
        if (inventoryService.getStock(code) <= 0) {
            throw new OutOfStockException("Produto sem stock: " + product.getCode());
        }
        if (!paymentStrategy.pay(product.getPrice())) {
            throw new InsufficientFundsException("Saldo insuficiente para comprar: " + product.getName());
        }
        inventoryService.dispenseProduct(code);
        return product;
    }

    /**
     * Retorna o serviço de inventário da máquina
     * 
     * @return	serviço de inventário
     */
    public InventoryService getInventoryService() {
        return inventoryService;
    }
}

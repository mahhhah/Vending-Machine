package com.vendingmachine.vending_machine.service;

import com.vendingmachine.vending_machine.exception.InsufficientFundsException;
import com.vendingmachine.vending_machine.exception.OutOfStockException;
import com.vendingmachine.vending_machine.model.Product;
import com.vendingmachine.vending_machine.strategy.PaymentStrategy;

public class VendingMachine {
    private static final VendingMachine INSTANCE = new VendingMachine(new InventoryService());

    private final InventoryService inventoryService;

    public VendingMachine(InventoryService inventoryService) {
        if (inventoryService == null) {
            throw new IllegalArgumentException("O serviço de inventário é obrigatório");
        }
        this.inventoryService = inventoryService;
    }

    // Singleton: existe uma máquina partilhada para a aplicação, quando necessário.
    public static VendingMachine getInstance() {
        return INSTANCE;
    }

    public void addProduct(Product product, int quantity) {
        inventoryService.addProduct(product, quantity);
    }

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

    public InventoryService getInventoryService() {
        return inventoryService;
    }
}

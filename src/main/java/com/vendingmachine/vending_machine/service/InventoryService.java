package com.vendingmachine.vending_machine.service;

import com.vendingmachine.vending_machine.exception.InvalidProductException;
import com.vendingmachine.vending_machine.model.Product;
import com.vendingmachine.vending_machine.model.Slot;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class InventoryService {
    private final Map<String, Slot> slots = new LinkedHashMap<>();

    public void addProduct(Product product, int quantity) {
        String code = normalizeCode(product.getCode());
        Slot existingSlot = slots.get(code);
        if (existingSlot == null) {
            slots.put(code, new Slot(product, quantity));
        } else {
            existingSlot.addStock(quantity);
        }
    }

    public Product getProduct(String code) {
        return getSlot(code).getProduct();
    }

    public int getStock(String code) {
        return getSlot(code).getQuantity();
    }

    public void dispenseProduct(String code) {
        getSlot(code).dispenseOne();
    }

    public Collection<Slot> listProducts() {
        return Collections.unmodifiableCollection(slots.values());
    }

    public void clear() {
        slots.clear();
    }

    private Slot getSlot(String code) {
        Slot slot = slots.get(normalizeCode(code));
        if (slot == null) {
            throw new InvalidProductException("Produto inválido: " + code);
        }
        return slot;
    }

    private String normalizeCode(String code) {
        if (code == null || code.isBlank()) {
            throw new InvalidProductException("Código de produto inválido");
        }
        return code.trim().toUpperCase();
    }
}

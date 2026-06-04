package com.vendingmachine.vending_machine.factory;

import com.vendingmachine.vending_machine.model.Product;

public final class ProductFactory {
    private ProductFactory() {
    }

    // Factory Method: centraliza a criação de produtos.
    public static Product createProduct(String code, String name, double price) {
        return new Product(code, name, price);
    }

    public static Product createDrink(String code, String name, double price) {
        return createProduct(code, name, price);
    }

    public static Product createSnack(String code, String name, double price) {
        return createProduct(code, name, price);
    }
}

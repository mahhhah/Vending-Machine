package com.vendingmachine.vending_machine.factory;

import com.vendingmachine.vending_machine.model.Product;


/**

 * Representa a fábrica dos produtos
 * Implementa o pattern Factory Method que centraliza a criação de produtos.
 */
public final class ProductFactory {
    
	/**
	 * Construtor privado porque esta classe não deve ser instanciada
	 */
	private ProductFactory() {
    }
    
    /**
     * Cria e retorna um novo produto com os dados fornecidos
     * 
     * @param code		código do produto
     * @param name		nome do produto
     * @param price		preço do produto
     * @return			nova instancia de Product
     * @throws IllegalArgumentException se o código ou o nome forem nulos ou vazios,
     * 									ou se o preço não for positivo
     */
    public static Product createProduct(String code, String name, double price) {
        return new Product(code, name, price);
    }

    /**
     * Cria e retorna uma nova bebida com os dados fornecidos
     * 
     * @param code		código da bebida
     * @param name		nome da bebida
     * @param price		preço da bebida
     * @return			nova instancia de Product representando uma bebida
     * @throws IllegalArgumentException se o código ou o nome forem nulos ou vazios,
     * 									ou se o preço não for positivo
     */
    public static Product createDrink(String code, String name, double price) {
        return createProduct(code, name, price);
    }

    /**
     * Cria e retorna um novo snack com os dados fornecidos
     * 
     * @param code		código do snack
     * @param name		nome do snack
     * @param price		preço do snack
     * @return			nova instancia de Product representando um snack
     * @throws IllegalArgumentException se o código ou o nome forem nulos ou vazios,
     * 									ou se o preço não for positivo
     */
    public static Product createSnack(String code, String name, double price) {
        return createProduct(code, name, price);
    }
}

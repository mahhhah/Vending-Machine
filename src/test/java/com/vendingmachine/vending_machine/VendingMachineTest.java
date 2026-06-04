package com.vendingmachine.vending_machine;

import com.vendingmachine.vending_machine.exception.InsufficientFundsException;
import com.vendingmachine.vending_machine.exception.InvalidProductException;
import com.vendingmachine.vending_machine.exception.OutOfStockException;
import com.vendingmachine.vending_machine.factory.ProductFactory;
import com.vendingmachine.vending_machine.model.Product;
import com.vendingmachine.vending_machine.service.InventoryService;
import com.vendingmachine.vending_machine.service.VendingMachine;
import com.vendingmachine.vending_machine.strategy.CardPayment;
import com.vendingmachine.vending_machine.strategy.CashPayment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendingMachineTest {
    private InventoryService inventoryService;
    private VendingMachine vendingMachine;

    @Before
    public void setUp() {
        inventoryService = new InventoryService();
        vendingMachine = new VendingMachine(inventoryService);
        vendingMachine.addProduct(ProductFactory.createDrink("A1", "Água", 1.20), 2);
        vendingMachine.addProduct(ProductFactory.createSnack("B1", "Chocolate", 1.50), 1);
    }

    @Test
    public void buyProductWithCashDecreasesStockAndBalance() {
        CashPayment cash = new CashPayment();
        cash.insertMoney(2.00);

        Product product = vendingMachine.buyProduct("A1", cash);

        assertEquals("Água", product.getName());
        assertEquals(1, inventoryService.getStock("A1"));
        assertEquals(0.80, cash.getBalance(), 0.001);
    }

    @Test
    public void buyProductWithCardUsesStrategy() {
        CardPayment card = new CardPayment("1234567812345678", 10.00);

        Product product = vendingMachine.buyProduct("B1", card);

        assertEquals("Chocolate", product.getName());
        assertEquals(0, inventoryService.getStock("B1"));
        assertEquals(8.50, card.getAvailableLimit(), 0.001);
        assertEquals("**** **** **** 5678", card.getMaskedCardNumber());
    }

    @Test(expected = InsufficientFundsException.class)
    public void cannotBuyWithInsufficientMoney() {
        CashPayment cash = new CashPayment();
        cash.insertMoney(0.50);

        vendingMachine.buyProduct("A1", cash);
    }

    @Test(expected = OutOfStockException.class)
    public void cannotBuyOutOfStockProduct() {
        CashPayment firstPayment = new CashPayment(2.00);
        CashPayment secondPayment = new CashPayment(2.00);

        vendingMachine.buyProduct("B1", firstPayment);
        vendingMachine.buyProduct("B1", secondPayment);
    }

    @Test(expected = InvalidProductException.class)
    public void cannotBuyInvalidProduct() {
        vendingMachine.buyProduct("Z9", new CashPayment(5.00));
    }

    @Test
    public void singletonReturnsSameSharedMachineInstance() {
        assertSame(VendingMachine.getInstance(), VendingMachine.getInstance());
    }
}

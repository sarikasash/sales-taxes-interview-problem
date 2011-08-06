package thoughtworks.problem.salesTax.application;

import java.math.BigDecimal;
import java.math.RoundingMode;

import thoughtworks.problem.salesTax.shoppingCart.ShoppingCart;

public class TransactionHandler {

	public static double getTotal(double cost, int quantity, double salesTaxPercent) {
		double originalCost = cost * quantity;
		double salesTaxAmount = (originalCost * salesTaxPercent) / 100;
		double roundedUpSalesTaxAmount = roundUp(salesTaxAmount);
		return (originalCost + roundedUpSalesTaxAmount);
	}

	private static double roundUp(double salesTaxAmount) {
		BigDecimal amount = new BigDecimal(salesTaxAmount);
		BigDecimal roundedValue = new BigDecimal(Math.ceil(amount.doubleValue() * 20) / 20);
		roundedValue.setScale(2, RoundingMode.HALF_UP);
		return roundedValue.doubleValue();
	}

	private ShoppingCart cart;

	public TransactionHandler(ShoppingCart cart) {
		this.cart = cart;

	}

}

package thoughtworks.problem.salesTax.application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

import thoughtworks.problem.salesTax.products.Product;
import thoughtworks.problem.salesTax.shoppingCart.ShoppingCart;

public class Receipt {

	private static class ReceiptItems {
		private int quantity;
		private String name;
		private double cost;
		private double salesTax;

		public ReceiptItems(int quantity, String name, double cost, double salesTax) {
			this.quantity = quantity;
			this.name = name;
			this.cost = cost;
			this.salesTax = salesTax;
		}

		public double getCost() {
			return this.cost;
		}

		public String getName() {
			return this.name;
		}

		public int getQuantity() {
			return this.quantity;
		}

		public double getSalesTax() {
			return this.salesTax;
		}
	}

	private ArrayList<ReceiptItems> receiptItemsList;
	private double salesTaxes;
	private double total;
	private ShoppingCart cart;

	public Receipt(ShoppingCart cart) {
		this.cart = cart;
		this.receiptItemsList = new ArrayList<ReceiptItems>();
		generateReceipt();
	}

	private void generateReceipt() {
		// Three things to do:
		// 1. List quantity, product name and total base cost per product
		Iterator<Product> cartIterator = cart.iterator();
		while (cartIterator.hasNext()) {
			Product product = cartIterator.next();
			int quantity = product.getQuantity();
			String name = product.getName();
			double cost = product.getCost();
			double salesTax = product.getSalesTax();

			receiptItemsList.add(new ReceiptItems(quantity, name, cost, salesTax));
		}

		// 2. Calculate total sales tax
		salesTaxes = getSalesTaxes();

		// 3. Calculate grand total including sales tax
		total = getTotal();
	}

	private double getSalesTaxes() {
		double salesTaxes = 0;
		Iterator<ReceiptItems> iterator = receiptItemsList.iterator();
		while (iterator.hasNext()) {
			salesTaxes += iterator.next().getSalesTax();
		}
		return salesTaxes;
	}

	private double getTotal() {
		double total = 0;
		Iterator<ReceiptItems> iterator = receiptItemsList.iterator();
		while (iterator.hasNext()) {
			total += iterator.next().getCost();
		}
		return (total + salesTaxes);
	}

	public void print() {
		// print the receipt and empty shopping cart
		Iterator<ReceiptItems> iterator = receiptItemsList.iterator();
		while (iterator.hasNext()) {
			ReceiptItems item = iterator.next();
			System.out.println(item.quantity + " " + item.name + " :" + item.cost);
		}

		System.out.println("\nSales Taxes:\t" + salesTaxes);
		System.out.println("Total:\t"
				+ new BigDecimal(Double.toString(total)).setScale(2, BigDecimal.ROUND_HALF_EVEN));
		cart.empty();
	}
}

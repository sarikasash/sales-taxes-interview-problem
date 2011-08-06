package thoughtworks.problem.salesTax.application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

import thoughtworks.problem.salesTax.constants.Constants;
import thoughtworks.problem.salesTax.products.Product;
import thoughtworks.problem.salesTax.shoppingCart.ShoppingCart;

public class Receipt {

	private static class ReceiptItems {
		private int quantity;
		private String name;
		private BigDecimal totalCost;
		private BigDecimal salesTax;

		public ReceiptItems(int quantity, String name, double salesTax, double totalCost) {
			this.quantity = quantity;
			this.name = name;
			this.salesTax = new BigDecimal(Double.toString(salesTax)).setScale(2,
					BigDecimal.ROUND_HALF_EVEN);
			this.totalCost = new BigDecimal(Double.toString(totalCost)).setScale(2,
					BigDecimal.ROUND_HALF_EVEN);
		}

	}

	private ArrayList<ReceiptItems> receiptItemsList;
	private double grandSalesTaxesTotal = 0;
	private double grandBaseCostTotal = 0;
	private double grandOverallTotal = 0;

	private ShoppingCart cart;

	public Receipt(ShoppingCart cart) {
		this.cart = cart;
		this.receiptItemsList = new ArrayList<ReceiptItems>();
		generateReceipt();
	}

	public BigDecimal getTotal() {
		BigDecimal total = new BigDecimal(Double.toString(grandOverallTotal)).setScale(
				Constants.REQUIRED_DECIMAL_PLACES, BigDecimal.ROUND_HALF_EVEN);
		return total;
	}

	public BigDecimal getSalesTax() {

		BigDecimal salesTaxes = new BigDecimal(Double.toString(grandSalesTaxesTotal)).setScale(
				Constants.REQUIRED_DECIMAL_PLACES, BigDecimal.ROUND_HALF_EVEN);
		return salesTaxes;

	}

	private void generateReceipt() {
		Iterator<Product> cartIterator = cart.iterator();

		while (cartIterator.hasNext()) {
			Product product = cartIterator.next();
			int quantity = product.getQuantity();
			String name = product.getName();
			double totalBaseCostForThisProduct = product.getCost();
			double totalSalesTaxForThisProduct = product.getSalesTax();
			double totalCostForThisProduct = totalBaseCostForThisProduct
					+ totalSalesTaxForThisProduct;

			grandSalesTaxesTotal += totalSalesTaxForThisProduct;
			grandBaseCostTotal += totalBaseCostForThisProduct;

			receiptItemsList.add(new ReceiptItems(quantity, name, totalSalesTaxForThisProduct,
					totalCostForThisProduct));
		}

		grandOverallTotal = grandBaseCostTotal + grandSalesTaxesTotal;
	}

	/**
	 * Method used by any printer to print the receipt
	 * */
	public void print() {
		Iterator<ReceiptItems> iterator = receiptItemsList.iterator();
		while (iterator.hasNext()) {
			ReceiptItems item = iterator.next();
			System.out.println(item.quantity + " " + item.name + " :" + item.totalCost);
		}

		System.out.println("\nSales Taxes:\t"
				+ new BigDecimal(Double.toString(grandSalesTaxesTotal)).setScale(2,
						BigDecimal.ROUND_HALF_EVEN));
		System.out.println("Total:\t"
				+ new BigDecimal(Double.toString(grandOverallTotal)).setScale(2,
						BigDecimal.ROUND_HALF_EVEN));
		cart.empty();
	}
}

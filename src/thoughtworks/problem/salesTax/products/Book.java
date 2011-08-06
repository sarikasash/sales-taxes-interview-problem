package thoughtworks.problem.salesTax.products;

import thoughtworks.problem.salesTax.constants.Constants;

public class Book extends Product {

	private double salesTaxPercent = Constants.SALES_TAX_FOR_BOOKS;

	public Book(String title, double cost, int quantity, boolean isImported) {
		super(title, cost, quantity, isImported);
	}

	@Override
	public double getSalesTax() {
		return this.salesTaxPercent;
	}

}

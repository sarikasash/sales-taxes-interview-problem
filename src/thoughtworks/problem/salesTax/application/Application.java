/**
 * Cashier gets a list of items from customer, adds them to shopping cart and generates a receipt
 * */

package thoughtworks.problem.salesTax.application;

import java.util.ArrayList;
import java.util.Date;

import thoughtworks.problem.salesTax.constants.Constants;
import thoughtworks.problem.salesTax.products.Product;
import thoughtworks.problem.salesTax.products.ProductFactory;
import thoughtworks.problem.salesTax.shoppingCart.CustomerCart;
import thoughtworks.problem.salesTax.shoppingCart.ShoppingCart;

public class Application {

	public static void main(String[] args) {

		// this portion provides a lookup table for item categories by their
		// keywords

		// read and prepare input data
		ArrayList<InputData> inputData = new FileParser("Inputs/Input.txt").getInputData();
		ProductFactory productFactory = ProductFactory.getInstance();
		ShoppingCart shoppingCart = new CustomerCart("Customer A", "Cashier B",
				new Date().toString());

		for (InputData data : inputData) {
			System.out.print("Quantity=" + data.getQuantity());
			System.out.print(";Product=" + data.getProduct());
			System.out.print(";Cost=" + data.getCost());
			System.out.println(";Imported=" + data.isImported());
		}

		Product book = productFactory.createProduct(Constants.OFFICE_SUPPLIES, "The Fountainhead",
				12.49, 1, false);
		Product musicCD = productFactory.createProduct(Constants.OTHER, "Beetles", 14.99, 1, false);

		Product chocolateBar = productFactory.createProduct(Constants.FOOD_PRODUCT,
				"Ferrero Rochers", 0.85, 1, false);

		shoppingCart.addProduct(book);
		shoppingCart.addProduct(musicCD);
		shoppingCart.addProduct(chocolateBar);

		Receipt receipt = new Receipt(shoppingCart);
		new Printer(receipt).print();

	}
}

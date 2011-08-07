/**
 * Cashier gets a list of items from customer, adds them to shopping cart and generates a receipt
 * */

package thoughtworks.problem.salesTax.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import thoughtworks.problem.salesTax.exceptions.InvalidInputException;
import thoughtworks.problem.salesTax.products.Product;
import thoughtworks.problem.salesTax.products.ProductFactory;
import thoughtworks.problem.salesTax.shoppingCart.CustomerCart;
import thoughtworks.problem.salesTax.shoppingCart.ShoppingCart;

public class Client {
	CategoryLookup lookup;
	ArrayList<InputData> inputData;
	ProductFactory productFactory;
	ShoppingCart shoppingCart;
	Receipt receipt;

	private String inputFilePath;

	public Client(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	public Receipt performTransaction(String customerName, String ClientName)
			throws InvalidInputException, NumberFormatException, IOException {
		// read and prepare input data
		lookup = CategoryLookup.getInstance();

		inputData = new FileParser(inputFilePath).getInputData();
		productFactory = ProductFactory.getInstance();
		shoppingCart = new CustomerCart(customerName, customerName, new Date().toString());

		for (InputData data : inputData) {
			int quantity = data.getQuantity();
			String productDescription = data.getProductDescription();
			double cost = data.getCost();
			boolean isImported = data.isImported();
			String category = lookup.getCategoryFor(productDescription);

			Product product = productFactory.createProduct(category, productDescription, cost,
					quantity, isImported);
			shoppingCart.addProduct(product);
		}

		try {
			receipt = new Receipt(shoppingCart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return receipt;
	}

}

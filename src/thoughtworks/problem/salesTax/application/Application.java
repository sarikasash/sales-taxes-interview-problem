package thoughtworks.problem.salesTax.application;

import java.io.FileNotFoundException;
import java.io.IOException;

import thoughtworks.problem.salesTax.exceptions.InvalidInputException;

public class Application {

	public static void main(String[] args) {
		Client client = null;
		Receipt receipt = null;
		String customerName = "CustomerA";
		String cashierName = "CashierA";
		client = new Client("Inputs/input1.txt");
		// client = new Client("Inputs/input2.txt");
		// client = new Client("Inputs/input3.txt");
		// client = new Client("Inputs/invalid_input.txt");
		// client = new Client("Inputs/input4.txt");

		if (client != null) {
			try {
				receipt = client.performTransaction(customerName, cashierName);
				new Printer(receipt).print();
			} catch (InvalidInputException invalidInputException) {
				System.out.println("Please check your input file for correct format");
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println("The file you specified, doesn't exist!");

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			System.out.println("Client Not Available");

	}
}

package thoughtworks.problem.salesTax.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;

import thoughtworks.problem.salesTax.application.Client;
import thoughtworks.problem.salesTax.application.Receipt;
import thoughtworks.problem.salesTax.exceptions.InvalidInputException;

public class InputTests {

	@Test
	public void testInput1() throws InvalidInputException, NumberFormatException, IOException {
		Client newClient = new Client("Inputs/input1.txt");
		Receipt receipt = newClient.performTransaction();
		BigDecimal total = receipt.getTotal();
		BigDecimal myTotal = new BigDecimal("29.83");
		assertTrue(total.equals(myTotal));
	}

	@Test
	public void testInput2() throws InvalidInputException, NumberFormatException, IOException {
		Client newClient = new Client("Inputs/input2.txt");
		Receipt receipt = newClient.performTransaction();
		BigDecimal total = receipt.getTotal();
		BigDecimal myTotal = new BigDecimal("65.15");
		assertTrue(total.equals(myTotal));
	}

	@Test
	public void testInput3() throws InvalidInputException, NumberFormatException, IOException {
		Client newClient = new Client("Inputs/input3.txt");
		Receipt receipt = newClient.performTransaction();
		BigDecimal total = receipt.getTotal();
		BigDecimal myTotal = new BigDecimal("74.68");
		assertTrue(total.equals(myTotal));
	}

	@Test
	public void testInvalidInputData() throws InvalidInputException {
		Client newClient = new Client("Inputs/invalid_input.txt");
		try {
			newClient.performTransaction();

		} catch (Exception invalidInputException) {
			assertEquals(invalidInputException.getMessage(), "Invalid file format");

		}
	}

	@Test
	public void testInvalidFile() throws FileNotFoundException {
		String invalidFilePath = "Inputs/non_existant_file.txt";
		Client newClient = new Client(invalidFilePath);
		try {
			newClient.performTransaction();
		} catch (Exception notFoundException) {
			assertEquals(notFoundException.getMessage(), invalidFilePath + " "
					+ "(No such file or directory)");

		}
	}

}

package thoughtworks.problem.salesTax.test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.problem.salesTax.application.Client;
import thoughtworks.problem.salesTax.application.Receipt;

public class InputTests {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInput1() {
		Client newClient = new Client("Inputs/input1.txt");
		Receipt receipt = newClient.performTransaction();
		BigDecimal total = receipt.getTotal();
		BigDecimal myTotal = new BigDecimal("29.83");
		assertTrue(total.equals(myTotal));
	}

	@Test
	public void testInput2() {
		Client newClient = new Client("Inputs/input2.txt");
		Receipt receipt = newClient.performTransaction();
		BigDecimal total = receipt.getTotal();
		BigDecimal myTotal = new BigDecimal("65.15");
		assertTrue(total.equals(myTotal));
	}

	@Test
	public void testInput3() {
		Client newClient = new Client("Inputs/input3.txt");
		Receipt receipt = newClient.performTransaction();
		BigDecimal total = receipt.getTotal();
		BigDecimal myTotal = new BigDecimal("74.68");
		assertTrue(total.equals(myTotal));
	}

}

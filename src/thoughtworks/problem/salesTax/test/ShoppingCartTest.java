package thoughtworks.problem.salesTax.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import thoughtworks.problem.salesTax.application.Client;
import thoughtworks.problem.salesTax.application.Receipt;

public class ShoppingCartTest {

	private Client client = null;
	private Receipt receipt = null;
	private String inputFilePath = "Inputs/input1.txt";
	private String fileExpectedTotal = "29.83";
	private String fileExpectedSalesTax = "1.50";

	@Before
	public void setUp() throws Exception {
		client = new Client(inputFilePath);
		receipt = client.performTransaction();
	}

	@Test
	public void testReceiptNotNull() {
		assertNotNull(receipt);
	}

	@Test
	public void testReceiptTotal() {
		BigDecimal total = receipt.getTotal();
		BigDecimal expectedTotal = new BigDecimal(fileExpectedTotal);
		assertEquals(total, expectedTotal);
	}

	@Test
	public void testReceiptSalesTax() {
		BigDecimal salesTax = receipt.getSalesTax();
		BigDecimal expectedSalesTax = new BigDecimal(fileExpectedSalesTax);
		assertEquals(salesTax, expectedSalesTax);
	}

}

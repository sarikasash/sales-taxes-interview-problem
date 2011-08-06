package thoughtworks.problem.salesTax.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import thoughtworks.problem.salesTax.constants.Constants;
import thoughtworks.problem.salesTax.products.Product;
import thoughtworks.problem.salesTax.products.ProductFactory;

public class ProductsTest {

	private ProductFactory factory;
	private Product product;

	@Before
	public void setUp() throws Exception {
		factory = ProductFactory.getInstance();
	}

	@After
	public void tearDown() {
		factory = null;
	}

	@Test
	public void testBookObject() {
		product = factory.createProduct(Constants.OFFICE_SUPPLIES, "The Fountainhead", 25.00, 2,
				false);
		assertEquals(product.getName(), "The Fountainhead");
		assertTrue(product.getSalesTax() == 0);
		assertTrue(product.getCost() == (25 * 2));

	}

	@Test
	public void testImportedBookObject() {
		product = factory.createProduct(Constants.OFFICE_SUPPLIES, "The Fountainhead", 25.00, 2,
				true);
		double salesTax = (25.00 * 2 * Constants.IMPORT_DUTY_FOR_IMPORTED) / 100;
		assertEquals(product.getName(), "The Fountainhead");
		assertTrue(product.getSalesTax() == salesTax);
		assertTrue(product.getCost() == (25 * 2));

	}

	@Test
	public void testMedicalObject() {
		product = factory.createProduct(Constants.MEDICAL_PRODUCT, "Band Aid Pack", 5.00, 1, false);
		assertEquals(product.getName(), "Band Aid Pack");
		assertTrue(product.getSalesTax() == 0);
		assertTrue(product.getCost() == 5.00);

	}

	@Test
	public void testOtherObject() {
		product = factory.createProduct(Constants.OTHER, "Chair", 15.00, 6, false);
		double totalSalesTax = (15.00 * 6 * Constants.SALES_TAX_FOR_DEFAULT) / 100;
		assertEquals(product.getName(), "Chair");
		assertTrue(product.getSalesTax() == totalSalesTax);
		assertTrue(product.getCost() == (15.00 * 6));

	}

	@Test
	public void testUnspecifiedObjectDefaultsToOther() {
		product = factory.createProduct("", "Chair", 15.00, 6, false);
		double totalSalesTax = (15.00 * 6 * Constants.SALES_TAX_FOR_DEFAULT) / 100;
		assertEquals(product.getName(), "Chair");
		assertTrue(product.getSalesTax() == totalSalesTax);
		assertTrue(product.getCost() == (15.00 * 6));

	}

}

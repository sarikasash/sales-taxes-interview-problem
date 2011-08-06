package thoughtworks.problem.salesTax.shoppingCart;

import java.util.Iterator;

import thoughtworks.problem.salesTax.products.Product;

public interface ShoppingCart {

	void addProduct(Product product);

	int getItemCount();

	Iterator iterator();

	void getProduct(Product product);

	void empty();

	void removeProduct(Product cancelledProduct);
}

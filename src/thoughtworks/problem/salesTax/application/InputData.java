package thoughtworks.problem.salesTax.application;

public class InputData {
	private int quantity;
	private String product;
	private boolean isImported;
	private double cost;

	public InputData() {
	}

	public InputData(int quantity, String product, boolean isImported, double cost) {
		super();
		this.quantity = quantity;
		this.product = product;
		this.isImported = isImported;
		this.cost = cost;
	}

	public double getCost() {
		return cost;
	}

	public String getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public boolean isImported() {
		return isImported;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
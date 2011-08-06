package thoughtworks.problem.salesTax.exceptions;

public class InvalidInputException extends Exception {

	@Override
	public String getMessage() {
		// Can log these messages to a log file
		return "Invalid file format";
	}

	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}

}

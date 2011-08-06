package thoughtworks.problem.salesTax.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import thoughtworks.problem.salesTax.exceptions.InvalidInputException;

public class FileParser {

	private FileReader fileReader;
	private BufferedReader bufferedReader;
	private String filePath;
	private ArrayList<InputData> inputDataArray;

	public FileParser(String filePath) throws InvalidInputException, NumberFormatException,
			IOException {
		this.filePath = filePath;
		this.inputDataArray = new ArrayList<InputData>();
		fileReader = new FileReader(filePath);
		bufferedReader = new BufferedReader(fileReader);
		String currentLine = null;
		int quantity = 0;
		double cost = 0;
		while ((currentLine = bufferedReader.readLine()) != null) {
			currentLine = currentLine.replaceAll("\\s+$", "");
			String[] array = currentLine.split(" ");

			if (isNumber(array[0]) && isNumber(array[array.length - 1])) {
				quantity = new Integer(array[0]).intValue();
				cost = new Double(array[array.length - 1]).doubleValue();
			} else {
				quantity = 0;
				cost = 0;
				throw new InvalidInputException();
			}

			String[] product = Arrays.copyOfRange(array, 1, array.length - 2);
			String productDescription = "";
			for (int index = 0; index < product.length; index++) {
				productDescription += " " + product[index].toLowerCase();
			}

			boolean isImported = productDescription.contains("imported")
					|| productDescription.contains("Imported");
			String productName = productDescription.replaceAll("[i,I]mported", "");
			inputDataArray.add(new InputData(quantity, productName, isImported, cost));
		}

	}

	private boolean isNumber(String string) {
		boolean isNumber = true;
		try {
			Double.parseDouble(string);
		} catch (NumberFormatException e) {
			isNumber = false;
		}
		return isNumber;
	}

	public ArrayList<InputData> getInputData() {
		return inputDataArray;

	}
}

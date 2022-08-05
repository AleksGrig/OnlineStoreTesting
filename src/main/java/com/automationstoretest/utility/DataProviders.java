package com.automationstoretest.utility;

import org.testng.annotations.DataProvider;

public class DataProviders {
	private static ExcelLibrary excelLibrary;

	static {
		String path = System.getProperty("user.dir") + "\\resources\\testdata.xlsx";
		excelLibrary = new ExcelLibrary(path);
	}

		
	@DataProvider(name = "credentials")
	public Object[][] getCredentials() {
		int totalRows = excelLibrary.getRowCount("Credentials") - 1;
		int totalColumn = excelLibrary.getColumnCount("Credentials");
		Object[][] data = new Object[totalRows][totalColumn];

		for (int i = 0; i < totalRows; i++) {
			for (int j = 0; j < totalColumn; j++) {
				data[i][j] = excelLibrary.getCellData("Credentials", j, i + 2);
			}
		}
		return data;
	}

	@DataProvider(name = "email")
	public Object[][] getEmail() {
		int totalRows = excelLibrary.getRowCount("Email") - 1;
		int totalColumns = excelLibrary.getColumnCount("Email");
		Object[][] data = new Object[totalRows][totalColumns];

		for (int i = 0; i < totalRows; i++) {
			for (int j = 0; j < totalColumns; j++) {
				data[i][j] = excelLibrary.getCellData("Email", j, i + 2);
			}
		}
		return data;
	}

	@DataProvider(name = "product")
	public Object[][] getProduct() {
		int totalRows = excelLibrary.getRowCount("ProductDetails") - 1;
		int totalColumns = excelLibrary.getColumnCount("ProductDetails");
		Object[][] data = new Object[totalRows][totalColumns];

		for (int i = 0; i < totalRows; i++) {
			for (int j = 0; j < totalColumns; j++) {
				data[i][j] = excelLibrary.getCellData("ProductDetails", j, i + 2);
			}
		}
		return data;
	}

	@DataProvider(name = "search")
	public Object[][] getProductPrice() {
		int totalRows = excelLibrary.getRowCount("SearchProduct") - 1;
		int totalColumns = excelLibrary.getColumnCount("SearchProduct");
		Object[][] data = new Object[totalRows][totalColumns];

		for (int i = 0; i < totalRows; i++) {
			for (int j = 0; j < totalColumns; j++) {
				data[i][j] = excelLibrary.getCellData("SearchProduct", j, i + 2);
			}
		}
		return data;
	}
}

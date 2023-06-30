package com.tutorialsninja.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {


	public static final int IMPLICITY_WAIT_TIME=10;
	public static final int PAGE_LOAD_WAIT=5;


	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "praveen"+timeStamp+"@gmail.com";
	}

	public static Object[][] getTestDataFromExcel(String SheetName) {
		XSSFWorkbook workbook=null;
		try {
		File testDataFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TestDataForLogin.xlsx");
		FileInputStream excelFis = new FileInputStream(testDataFile);
		workbook = new XSSFWorkbook(excelFis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		

		XSSFSheet sheet = workbook.getSheet(SheetName);

		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][columns];

		for(int i=0;i<rows;i++) {
			XSSFRow row = sheet.getRow(i+1);

			for(int j=0;j<columns;j++) {
				XSSFCell cell = row.getCell(j);

				CellType cellType = cell.getCellType();

				switch (cellType) {
				case STRING: 
					data[i][j]=cell.getStringCellValue();
					break;
				case NUMERIC: 
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					break;
				}
			}
		}


		return data;
	}

	
}

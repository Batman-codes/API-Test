package utils;

import org.testng.annotations.DataProvider;

public class TestDataUtil {
	
	@DataProvider(name = "GetTtData")
	public String[][] getTestData(String fileName, String sheetName) {
		fileName = "CreateUserTestData";
		sheetName = "Sheet1";
		ExcelUtil excelUtil = new ExcelUtil();
		
		return excelUtil.readExcelData(fileName, sheetName);
		
	}

}

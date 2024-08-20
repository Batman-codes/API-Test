package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	protected String[][] testData = null;
	
	public String[][] readExcelData(String fileName , String mySheet){
		
		try {
			workbook = new XSSFWorkbook("./src/test/resources/testdata/" + fileName + ".xlsx");
			
			sheet = workbook.getSheet(mySheet);
			
			int cells = sheet.getRow(0).getLastCellNum();
			
			int rows = sheet.getLastRowNum();
			
			System.out.println("Rows : " + rows + " cellCount : " + cells);
			
			testData = new String[rows][cells];
			for(int i = 1 ; i <= rows ; i++) {
				for(int j = 0; j< cells ; j++) {
					testData[i-1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return testData;
		
	}

}

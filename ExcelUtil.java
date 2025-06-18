package Homepackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import Homepackage.ContactPage;
public class ExcelUtil {
	 public static List<Map<String, String>> getTestData(String excelPath,String sheetName) {
	        List<Map<String, String>> testDataAllRows = new ArrayList<>();
 
	        try (FileInputStream fis = new FileInputStream(excelPath)) {
	            Workbook workbook = WorkbookFactory.create(fis);
	            Sheet sheet = workbook.getSheet(sheetName);
	            Row headerRow = sheet.getRow(0);
 
	            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	                Map<String, String> testData = new HashMap<>();
	                Row row = sheet.getRow(i);
	                if(row==null)
	                	continue;
	                for (int j = 0; j < headerRow.getLastCellNum(); j++) {
	                	Cell cell=row.getCell(j,MissingCellPolicy.CREATE_NULL_AS_BLANK);
	                    testData.put(headerRow.getCell(j).getStringCellValue(),cell.toString());
	                }
	                testDataAllRows.add(testData);
	            }
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	        }
 
	        return testDataAllRows;
	    }
}
